Class dc.irishiveadapter.HiveProduction Extends Ens.Production
{

XData ProductionDefinition
{
<Production Name="dc.irishiveadapter.HiveProduction" LogGeneralTraceEvents="false">
  <Description></Description>
  <ActorPoolSize>2</ActorPoolSize>
  <Item Name="HiveService" Category="" ClassName="dc.irishiveadapter.HiveService" PoolSize="1" Enabled="true" Foreground="false" Comment="" LogTraceEvents="false" Schedule=""> 
    <Setting Target="Host" Name="HiveConnection">jdbc:hive2://localhost:10000/;ssl=false</Setting>
  </Item>
  <Item Name="HiveOperation" Category="" ClassName="EnsLib.PEX.BusinessOperation" PoolSize="1" Enabled="true" Foreground="false" Comment="" LogTraceEvents="true" Schedule="">
    <Setting Target="Host" Name="%gatewayPort">55555</Setting>
    <Setting Target="Host" Name="%remoteClassname">dc.irishiveadapter.HiveOperation</Setting>
    <Setting Target="Host" Name="%gatewayExtraClasspaths"></Setting>
    <Setting Target="Host" Name="%gatewayHost">jgw</Setting>
  </Item> 
</Production>
}

}
