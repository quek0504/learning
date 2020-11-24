CREATE DATABASE IF NOT EXISTS `mall_ums`; /*user management system*/

USE `mall_ums`;

drop table if exists ums_growth_change_history;

drop table if exists ums_integration_change_history;

drop table if exists ums_member;

drop table if exists ums_member_collect_spu;

drop table if exists ums_member_collect_subject;

drop table if exists ums_member_level;

drop table if exists ums_member_login_log;

drop table if exists ums_member_receive_address;

drop table if exists ums_member_statistics_info;

/*==============================================================*/
/* Table: ums_growth_change_history                             */
/*==============================================================*/
create table ums_growth_change_history
(
   id                   bigint not null auto_increment,
   member_id            bigint,
   create_time          datetime,
   change_count         int comment 'positive/negative',
   note                 varchar(0),
   source_type          tinyint comment '[0-shopping，1-admin]',
   primary key (id)
);

alter table ums_growth_change_history;

/*==============================================================*/
/* Table: ums_integration_change_history                        */
/*==============================================================*/
create table ums_integration_change_history
(
   id                   bigint not null auto_increment,
   member_id            bigint,
   create_time          datetime,
   change_count         int,
   note                 varchar(255),
   source_tyoe          tinyint comment '[0-shopping，1-admin, 2-events]',
   primary key (id)
);

alter table ums_integration_change_history;

/*==============================================================*/
/* Table: ums_member                                            */
/*==============================================================*/
create table ums_member
(
   id                   bigint not null auto_increment,
   level_id             bigint,
   username             char(64),
   password             varchar(64),
   nickname             varchar(64),
   mobile               varchar(20),
   email                varchar(64),
   header               varchar(500),
   gender               tinyint,
   birth                date,
   city                 varchar(500),
   job                  varchar(255),
   sign                 varchar(255) comment 'signature',
   source_type          tinyint,
   integration          int comment 'member points',
   growth               int comment 'growth points',
   status               tinyint,
   create_time          datetime,
   primary key (id)
);

alter table ums_member;

/*==============================================================*/
/* Table: ums_member_collect_spu                                */
/*==============================================================*/
create table ums_member_collect_spu
(
   id                   bigint not null,
   member_id            bigint,
   spu_id               bigint,
   spu_name             varchar(500),
   spu_img              varchar(500),
   create_time          datetime,
   primary key (id)
);

alter table ums_member_collect_spu comment 'saved spu';

/*==============================================================*/
/* Table: ums_member_collect_subject                            */
/*==============================================================*/
create table ums_member_collect_subject
(
   id                   bigint not null auto_increment,
   subject_id           bigint,
   subject_name         varchar(255),
   subject_img          varchar(500),
   subject_url          varchar(500),
   primary key (id)
);

alter table ums_member_collect_subject comment 'saved event';

/*==============================================================*/
/* Table: ums_member_level                                      */
/*==============================================================*/
create table ums_member_level
(
   id                   bigint not null auto_increment,
   name                 varchar(100) comment 'level naming',
   growth_point         int comment 'to level up',
   default_status       tinyint comment '[0->no；1->yes]',
   free_freight_point   decimal(18,4),
   comment_growth_point int,
   priviledge_free_freight tinyint,
   priviledge_member_price tinyint,
   priviledge_birthday  tinyint,
   note                 varchar(255),
   primary key (id)
);

alter table ums_member_level;

/*==============================================================*/
/* Table: ums_member_login_log                                  */
/*==============================================================*/
create table ums_member_login_log
(
   id                   bigint not null auto_increment,
   member_id            bigint,
   create_time          datetime,
   ip                   varchar(64),
   city                 varchar(64),
   login_type           tinyint(1) comment '[1-web，2-app]',
   primary key (id)
);

alter table ums_member_login_log;

/*==============================================================*/
/* Table: ums_member_receive_address                            */
/*==============================================================*/
create table ums_member_receive_address
(
   id                   bigint not null auto_increment,
   member_id            bigint,
   name                 varchar(255) comment 'receiver name',
   phone                varchar(64) comment 'receiver phone',
   post_code            varchar(64),
   state                varchar(100),
   city                 varchar(100),
   detail_address       varchar(255),
   areacode             varchar(15),
   default_status       tinyint(1),
   primary key (id)
);

alter table ums_member_receive_address;

/*==============================================================*/
/* Table: ums_member_statistics_info                            */
/*==============================================================*/
create table ums_member_statistics_info
(
   id                   bigint not null auto_increment,
   member_id            bigint,
   consume_amount       decimal(18,4),
   coupon_amount        decimal(18,4),
   order_count          int,
   coupon_count         int,
   comment_count        int,
   return_order_count   int,
   login_count          int,
   attend_count         int comment 'follow number',
   fans_count           int,
   collect_product_count int,
   collect_subject_count int,
   collect_comment_count int,
   invite_friend_count  int,
   primary key (id)
);

alter table ums_member_statistics_info;