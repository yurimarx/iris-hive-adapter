Class dc.irishiveadapter.HiveRequest Extends Ens.Request
{

Property SQL As %String(MAXLEN = "");

Property Connection As %String;

Property Type As %String;

Storage Default
{
<Data name="HiveRequestDefaultData">
<Subscript>"HiveRequest"</Subscript>
<Value name="1">
<Value>SQL</Value>
</Value>
<Value name="2">
<Value>Connection</Value>
</Value>
<Value name="3">
<Value>Type</Value>
</Value>
</Data>
<DefaultData>HiveRequestDefaultData</DefaultData>
<Type>%Storage.Persistent</Type>
}

}
