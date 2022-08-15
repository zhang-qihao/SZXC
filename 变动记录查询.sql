select selectedkey, �䶯����, �����, ���ʱ��, �������, �������, ���ǰ, ����� from (
select distinct
  decode(a.ADA137, 'GW01', '01', 'KS01', '02', '00') as selectedkey,
  decode(a.ADA137, 'GW01', '��λ��Ϣ�䶯', 'KS01', '������Ϣ�䶯', '��Ա��Ϣ�䶯') as �䶯����,
  ADA3.CAE251 as �����,
  ADA3.AAE035 as ���ʱ��,
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
  end as �������,
  decode(a.ada137, 'GW10', decode(ADA3.CAE059, '1', '�����λ', '3', '���', a.ADA181),
                   null, null,
                   decode(ADA3.CAE059, '1', '����', '3', 'ɾ��', a.ADA181)
  ) as �������,
  decode(ADA3.CAE059, '3', decode(a.ada137�� 'GW10', (select g.gw003 from AC01_GW p, GW01 g, GW10 t
                                                            where t.ac01id = p.ac01id
                                                            and t.gw001 = g.gw001
                                                            and t.gw100 = a.gwid),
                                              null, null, 
                                              null),
                      '1', null,
                      a.ADA127
  ) as ���ǰ,
  decode(ADA3.CAE059, '1', decode(a.ada137�� 'GW10', (select g.gw003 from AC01_GW p, GW01 g, GW10 t
                                                            where t.ac01id = p.ac01id
                                                            and t.gw001 = g.gw001
                                                            and t.gw100 = a.gwid),
                                              null, null, 
                                              null),
                      '3', null,
                      a.ADA152
  ) as �����
from ADA3, ADA4 a
where 
     ADA3.ADA003 = a.ADA003
)
