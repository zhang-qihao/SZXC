select ks001, -- 科室id
       ks003, -- 科室名称
       ks009, -- 科室分管主任id
       ks010, -- 科室分管主任姓名
       ks019, -- 科室负责人id
       ks020, -- 科室负责人姓名
       aae036, -- 创建时间
       gwnum, -- 科室岗位数
       pernum, -- 科室人数
       ks002, -- 科室职责
       ckc005 -- 状态（00-已启用，01-已停用）
  from (select ks001,
               ks003,
               ks009,
               ks010,
               ks019,
               ks020,
               to_char(aae036, 'YYYY-MM-DD') as aae036,
               (select count(*) from GW01 where GW01.ks001 = ks01.ks001) as gwnum,
               (select count(*) from GW10 where GW10.ks001 = ks01.ks001) as pernum,
               ks002,
               ckc005
          from ks01 order by aae036 desc)
