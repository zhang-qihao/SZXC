select ks001, gw001, gw003, gw002, ac01id, aac003, aac004, aac039, aac029, applyt, passt, createt
from (
select a.ks001 as ks001,        -- 科室ID(隐藏)
       a.GW001 as gw001,        -- 岗位ID(隐藏)
       a.GW003 as gw003,        -- 岗位名称
       a.GW002 as gw002,        -- 岗位职责
       b.AC01ID as ac01id,      -- 人员用户ID(隐藏)
       c.AAC003 as aac003,      -- 工作人员
       c.AAC004 as aac004,      -- 人员性别
       c.AAC039 as aac039,      -- 人员性质
       c.AAC029 as aac029,      -- 政治面貌
       b.AAE036 as applyt,      -- 申请进入该岗时间
       b.AAE036 as passt,       -- 审核通过时间
       a.AAE036 as createt      -- 创建时间(隐藏)
from GW01 a, GW10 b, AC01_GW c
where 
     a.GW001 = b.GW001
     and b.Ac01id = c.AC01ID
order by -- 按岗分组，组内按时间排序
     (case when a.GW003 = '科室管理岗' then 1 else 2 end),
     a.GW003,
     b.AAE036
)
