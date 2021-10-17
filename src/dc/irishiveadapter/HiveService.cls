Class dc.irishiveadapter.HiveService Extends Ens.BusinessService
{

// extends Ens.BusinessService to create a custom Business service using Object Script

// This class receive a sql from a http request and return the results

// choose an adapter to get data from a source of data

// HTTP.InboundAdapter allows you get data from an http request

Parameter ADAPTER = "EnsLib.HTTP.InboundAdapter";

// custom parameter to allows production user set hive server 
Property Connection As %String(MAXLEN = 255);

// custom parameter to allows production user set hive server 
Property Type As %String(MAXLEN = 4);

// when you set parameter Connection and Type to SETTINGS parameter, the production IRIS interface create a field to the user fills
// so the user will inform server connection and type for the hive request 

Parameter SETTINGS = "Connection,Type";

// This method is mandatory to have a business service. It receives the multipart file into pInput 

// and returns a result to the caller using pOutput
Method OnProcessInput(pInput As %GlobalCharacterStream, Output pOutput As %RegisteredObject) As %Status
{
    //try to do the actions
    try {

        //get contents of inbound stream
        //in this case, the stream contains a single value: the SQL
        Set SQL=pInput.Read(,.tSC)

        //make sure Read went OK
        If $$$ISERR(tSC) do $System.Status.DisplayError(tSC)

        //create request object to send
        Set tRequest=##class(dc.irishiveadapter.HiveRequest).%New()
        Set tRequest.SQL = SQL
        Set tRequest.Connection = ..Connection
        Set tRequest.Type = ..Type

        //send to lookup process
        Set tSC=..SendRequestSync("EHTTP.LookupProcess",tRequest,.tResponse)

        //define output for OnProcessInput
        Set pOutput=tResponse

        Return tSC

    //returns error message to the user
    } catch e {
        Set tSC=e.AsStatus()
        Return tSC
    }

    Quit tSC
    
}


}
