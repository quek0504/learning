CREATE DATABASE  IF NOT EXISTS `mall_sms`; /* sales management system*/

USE `mall_sms`;

drop table if exists sms_coupon;

drop table if exists sms_coupon_history;

drop table if exists sms_coupon_spu_category_relation;

drop table if exists sms_coupon_spu_relation;

drop table if exists sms_home_adv;

drop table if exists sms_home_subject;

drop table if exists sms_home_subject_spu;

drop table if exists sms_member_price;

drop table if exists sms_seckill_promotion;

drop table if exists sms_seckill_session;

drop table if exists sms_seckill_sku_notice;

drop table if exists sms_seckill_sku_relation;

drop table if exists sms_sku_full_reduction;

drop table if exists sms_sku_ladder;

drop table if exists sms_spu_bonus;

/*==============================================================*/
/* Table: sms_coupon                                            */
/*==============================================================*/
create table sms_coupon
(
   id                   bigint not null auto_increment,
   coupon_type          tinyint(1) comment '[0->in mall；1->member；2->shopping；3->register]',
   coupon_img           varchar(2000),
   coupon_name          varchar(100),
   num                  int comment 'number',
   amount               decimal(18,4),
   claim_limit          int,
   min_point            decimal(18,4) comment 'min spend to use coupon',
   start_time           datetime,
   end_time             datetime,
   use_type             tinyint(1) comment '[0->in apps；1->specific category；2->specific product]',
   note                 varchar(200),
   publish_count        int(11),
   use_count            int(11),
   receive_count        int(11),
   enable_start_time    datetime comment 'start date to claim',
   enable_end_time      datetime comment 'end date to claim',
   code                 varchar(64) comment 'coupon code',
   member_level         tinyint(1) comment 'who can claim[0->anyone，different levels of members]',
   publish              tinyint(1) comment '[0-no，1-yes]',
   primary key (id)
);

alter table sms_coupon;

/*==============================================================*/
/* Table: sms_coupon_history                                    */
/*==============================================================*/
create table sms_coupon_history
(
   id                   bigint not null auto_increment,
   coupon_id            bigint,
   member_id            bigint,
   member_nick_name     varchar(64),
   get_type             tinyint(1) comment '[0->backend；1->use claim]',
   create_time          datetime,
   use_type             tinyint(1) comment '[0->ready to use；1->used；2->expired]',
   use_time             datetime,
   order_id             bigint,
   order_sn             bigint,
   primary key (id)
);

alter table sms_coupon_history;

/*==============================================================*/
/* Table: sms_coupon_spu_category_relation                      */
/*==============================================================*/
create table sms_coupon_spu_category_relation
(
   id                   bigint not null auto_increment,
   coupon_id            bigint,
   category_id          bigint,
   category_name        varchar(64),
   primary key (id)
);

alter table sms_coupon_spu_category_relation;

/*==============================================================*/
/* Table: sms_coupon_spu_relation                               */
/*==============================================================*/
create table sms_coupon_spu_relation
(
   id                   bigint not null auto_increment ,
   coupon_id            bigint,
   spu_id               bigint,
   spu_name             varchar(255),
   primary key (id)
);

alter table sms_coupon_spu_relation;

/*==============================================================*/
/* Table: sms_home_adv (advertisement)                          */
/*==============================================================*/
create table sms_home_adv
(
   id                   bigint not null auto_increment,
   name                 varchar(100),
   pic                  varchar(500),
   start_time           datetime,
   end_time             datetime,
   status               tinyint(1),
   click_count          int,
   url                  varchar(500),
   note                 varchar(500),
   sort                 int,
   publisher_id         bigint,
   auth_id              bigint,
   primary key (id)
);

alter table sms_home_adv;

/*==============================================================*/
/* Table: sms_home_subject                                      */
/*==============================================================*/
create table sms_home_subject
(
   id                   bigint not null auto_increment,
   name                 varchar(200),
   title                varchar(255),
   sub_title            varchar(255),
   status               tinyint(1) comment 'show status',
   url                  varchar(500),
   sort                 int,
   img                  varchar(500),
   primary key (id)
);

alter table sms_home_subject;

/*==============================================================*/
/* Table: sms_home_subject_spu                                  */
/*==============================================================*/
create table sms_home_subject_spu
(
   id                   bigint not null auto_increment,
   name                 varchar(200),
   subject_id           bigint,
   spu_id               bigint,
   sort                 int,
   primary key (id)
);

alter table sms_home_subject_spu;

/*==============================================================*/
/* Table: sms_member_price                                      */
/*==============================================================*/
create table sms_member_price
(
   id                   bigint not null auto_increment,
   sku_id               bigint,
   member_level_id      bigint,
   member_level_name    varchar(100),
   member_price         decimal(18,4),
   stack_other          tinyint(1),
   primary key (id)
);

alter table sms_member_price;

/*==============================================================*/
/* Table: sms_seckill_promotion                                 */
/*==============================================================*/
create table sms_seckill_promotion
(
   id                   bigint not null auto_increment,
   title                varchar(255),
   start_time           datetime,
   end_time             datetime,
   status               tinyint,
   create_time          datetime,
   user_id              bigint,
   primary key (id)
);

alter table sms_seckill_promotion;

/*==============================================================*/
/* Table: sms_seckill_session                                   */
/*==============================================================*/
create table sms_seckill_session
(
   id                   bigint not null auto_increment,
   name                 varchar(200),
   start_time           datetime,
   end_time             datetime,
   status               tinyint(1),
   create_time          datetime,
   primary key (id)
);

alter table sms_seckill_session;

/*==============================================================*/
/* Table: sms_seckill_sku_notice                                */
/*==============================================================*/
create table sms_seckill_sku_notice
(
   id                   bigint not null auto_increment,
   member_id            bigint,
   sku_id               bigint,
   session_id           bigint,
   subcribe_time        datetime,
   send_time            datetime,
   notice_type          tinyint(1) comment '[0-sms，1-email]',
   primary key (id)
);

alter table sms_seckill_sku_notice;

/*==============================================================*/
/* Table: sms_seckill_sku_relation                              */
/*==============================================================*/
create table sms_seckill_sku_relation
(
   id                   bigint not null auto_increment,
   promotion_id         bigint,
   promotion_session_id bigint,
   sku_id               bigint,
   seckill_price        decimal,
   seckill_count        decimal,
   seckill_limit        decimal,
   seckill_sort         int,
   primary key (id)
);

alter table sms_seckill_sku_relation;

/*==============================================================*/
/* Table: sms_sku_full_reduction                                */
/*==============================================================*/
create table sms_sku_full_reduction
(
   id                   bigint not null auto_increment,
   sku_id               bigint,
   full_price           decimal(18,4) comment 'target hit price',
   reduce_price         decimal(18,4) comment 'reduce price once target hit',
   stack_other          tinyint(1),
   primary key (id)
);

alter table sms_sku_full_reduction;

/*==============================================================*/
/* Table: sms_sku_ladder                                        */
/*==============================================================*/
create table sms_sku_ladder
(
   id                   bigint not null auto_increment,
   sku_id               bigint,
   full_count           int comment 'target hit count',
   discount             decimal(4,2),
   price                decimal(18,4) comment 'price after discount',
   stack_other          tinyint(1),
   primary key (id)
);

alter table sms_sku_ladder comment '商品阶梯价格';

/*==============================================================*/
/* Table: sms_spu_bonus                                        */
/*==============================================================*/
create table sms_spu_bonus
(
   id                   bigint not null auto_increment,
   spu_id               bigint,
   grow_bonus          decimal(18,4) comment 'growth points',
   buy_bonus           decimal(18,4),
   work                 tinyint(1)
   comment '优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]',
   primary key (id)
);

alter table sms_spu_bonus;