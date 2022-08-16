select ac01id,
       gw001,
       gw003,
       gw002,
       ks001,
       aae036,
       cae249,
       cae251,
       aae100,
       aaz002,
       aae013
  from (select gw10.ac01id,
               gw01.gw001,
               gw01.gw003,
               gw01.gw002,
               gw01.ks001,
               gw01.aae036,
               gw01.cae249,
               gw01.cae251,
               gw01.aae100,
               gw01.aaz002,
               gw01.aae013
          from gw01, gw10
         where gw01.gw001 = gw10.gw001)
