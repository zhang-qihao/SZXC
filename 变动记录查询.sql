select selectedkey, 变动类型, 变更人, 变更时间, 变更对象, 变更内容, 变更前, 变更后 from (
select distinct
  decode(a.ADA137, 'GW01', '01', 'KS01', '02', '00') as selectedkey,
  decode(a.ADA137, 'GW01', '岗位信息变动', 'KS01', '科室信息变动', '人员信息变动') as 变动类型,
  ADA3.CAE251 as 变更人,
  ADA3.AAE035 as 变更时间,
  case
    when a.ada137 = 'AC01_GW' then (select AAC003 from AC01_GW where AC01_GW.ac01id = a.Gwid)
    when a.ada137 = 'GW01' then (select GW003 from GW01 where GW01.GW001 = a.Gwid)
    when a.ada137 = 'KS01' then (select KS003 from KS01 where KS01.KS001 = a.Gwid)
    when a.ada137 = 'GW10' 
      then (
      select p.aac003 from AC01_GW p, GW01 g, GW10 t
      where t.ac01id = p.ac01id
      and t.gw001 = g.gw001
      and t.gw100 = a.gwid
      )
  end as 变更对象,
  decode(a.ada137, 'GW10', decode(ADA3.CAE059, '1', '分配岗位', '3', '离岗', a.ADA181),
                   null, null,
                   decode(ADA3.CAE059, '1', '新增', '3', '删除', a.ADA181)
  ) as 变更内容,
  decode(ADA3.CAE059, '3', decode(a.ada137， 'GW10', (select g.gw003 from AC01_GW p, GW01 g, GW10 t
                                                            where t.ac01id = p.ac01id
                                                            and t.gw001 = g.gw001
                                                            and t.gw100 = a.gwid),
                                              null, null, 
                                              null),
                      '1', null,
                      a.ADA127
  ) as 变更前,
  decode(ADA3.CAE059, '1', decode(a.ada137， 'GW10', (select g.gw003 from AC01_GW p, GW01 g, GW10 t
                                                            where t.ac01id = p.ac01id
                                                            and t.gw001 = g.gw001
                                                            and t.gw100 = a.gwid),
                                              null, null, 
                                              null),
                      '3', null,
                      a.ADA152
  ) as 变更后
from ADA3, ADA4 a
where 
     ADA3.ADA003 = a.ADA003
)
