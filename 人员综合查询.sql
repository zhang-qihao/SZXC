select AC01id, 姓名, 性别, 证件号码, 出生日期, 人员性质, 政治面貌, 职务, 任职务时间, 职级, 任职级时间, 所属科室, 所在岗位, 任岗时间 from (
select distinct
       p.AC01id,
       AAC003 as 姓名,
       AAC004 as 性别,
       AAC147 as 证件号码,
       to_date(AAC006, 'YYYY-MM-DD') as 出生日期,
       AAC039 as 人员性质,
       AAC029 as 政治面貌,
       AAC049 as 职务,
       to_date(AAC051, 'YYYY-MM-DD') as 任职务时间,
       AAC059 as 职级,
       to_date(AAC061, 'YYYY-MM-DD') as 任职级时间,
       KS001 as 所属科室,
       (
             select listagg(a.GW003, ',') 
             within group (order by a.GW003)
             from GW01 a, GW10 b
             where a.gw001 = b.gw001
             and b.ac01id = p.ac01id
             group by b.ac01id
       ) as 所在岗位,
       to_date(p.AAC051, 'YYYY-MM-DD') as 任岗时间
from AC01_GW p
)
