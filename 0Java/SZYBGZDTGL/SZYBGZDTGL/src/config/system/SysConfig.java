package config.system;

/**
 * Created by ZQ on 2017/1/10.
 */
public class SysConfig {


//  供应商	               Catalog支持	                        Schema支持
//  Oracle	               不支持                               Oracle User ID
//  MySQL	               不支持	                            数据库名
//  MS SQL Server	       数据库名	                            对象属主名，2005版开始有变
//  DB2	                   指定数据库对象时，Catalog部分省略	Catalog属主名
//  Sybase	               数据库名	                            数据库属主名
//  Informix	           不支持	                            不需要
//  PointBase	           不支持	                            数据库名


  /**SqlServer设置**/
//  public static final String DB_SYS_SCHEMA  = "dbo";                           //当Sql Server数据库时   可设置dbo      当Sql Server数据库时  可设置 Oracle User ID    可空，当空时默认业务数据所对应数据库用户
//  public static final String DB_SYS_CATALOG  = "eframe5";                     //    可空，当空时默认业务数据所对应数据库用户
//  public static final String DB_SYS_QUERY_CATALOG_SCHEMA  = "eframe5.dbo.";   //系统表查询所对应的表所属用户，       可空，当空时默认业务数据所对应数据库用户
//                                                                                    // 例如：Oracle数据库:  oa. 表示系统表在oa用户下， 若多系统公用系统表，可建立公共的系统数据库用户 如 sysproj.
                                                                                    //  Sql Server数据库:  oa.dbo. 表示系统表在oa用户下
                                                                                    //  默认为"",表示系统表与业务表在同一用户下

////  /**Oracle设置**/
//  public static final String DB_SYS_SCHEMA  = "eframe5";   //当Sql Server数据库时   可设置dbo      当Sql Server数据库时  可设置 Oracle User ID    可空，当空时默认业务数据所对应数据库用户
//  public static final String DB_SYS_CATALOG  = "";   //    可空，当空时默认业务数据所对应数据库用户
//  public static final String DB_SYS_QUERY_CATALOG_SCHEMA  = "eframe5.";   //系统表查询所对应的表所属用户，       可空，当空时默认业务数据所对应数据库用户
//                                                                                    // 例如：Oracle数据库:  oa. 表示系统表在oa用户下， 若多系统公用系统表，可建立公共的系统数据库用户 如 sysproj.
////                                                                                    //  Sql Server数据库:  oa.dbo. 表示系统表在oa用户下
////                                                                                    //  默认为"",表示系统表与业务表在同一用户下


  //  /**Oracle设置**/
  public static final String DB_SYS_SCHEMA  = "";   //当Sql Server数据库时   可设置dbo      当Sql Server数据库时  可设置 Oracle User ID    可空，当空时默认业务数据所对应数据库用户
  public static final String DB_SYS_CATALOG  = "";   //    可空，当空时默认业务数据所对应数据库用户
  public static final String DB_SYS_QUERY_CATALOG_SCHEMA  = "";   //系统表查询所对应的表所属用户，       可空，当空时默认业务数据所对应数据库用户
  // 例如：Oracle数据库:  oa. 表示系统表在oa用户下， 若多系统公用系统表，可建立公共的系统数据库用户 如 sysproj.
//                                                                                    //  Sql Server数据库:  oa.dbo. 表示系统表在oa用户下
//                                                                                    //  默认为"",表示系统表与业务表在同一用户下


}
