select ks001, -- ����id
       ks003, -- ��������
       ks009, -- ���ҷֹ�����id
       ks010, -- ���ҷֹ���������
       ks019, -- ���Ҹ�����id
       ks020, -- ���Ҹ���������
       aae036, -- ����ʱ��
       gwnum, -- ���Ҹ�λ��
       pernum, -- ��������
       ks002, -- ����ְ��
       ckc005 -- ״̬��00-�����ã�01-��ͣ�ã�
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
