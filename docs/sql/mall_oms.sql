CREATE DATABASE IF NOT EXISTS `mall_oms`; /* order management system */

USE `mall_oms`;
drop table if exists oms_order;

drop table if exists oms_order_item;

drop table if exists oms_order_operate_history;

drop table if exists oms_order_return_apply;

drop table if exists oms_order_return_reason;

drop table if exists oms_order_setting;

drop table if exists oms_payment_info;

drop table if exists oms_refund_info;

/*==============================================================*/
/* Table: oms_order                                             */
/*==============================================================*/
create table oms_order
(
   id                   bigint not null auto_increment,
   member_id            bigint,
   order_sn             char(32) comment 'order id',
   coupon_id            bigint,
   create_time          datetime,
   member_username      varchar(200),
   total_amount         decimal(18,4),
   pay_amount           decimal(18,4),
   freight_amount       decimal(18,4),
   promotion_amount     decimal(18,4),
   integration_amount   decimal(18,4) comment 'amount reduced by member points',
   coupon_amount        decimal(18,4),
   discount_amount      decimal(18,4),
   pay_type             tinyint,
   source_type          tinyint comment '[0->PC；1->app]',
   status               tinyint comment '[0->to pay；1->to ship；2->delivered；3->completed；4->closed；5->invalid order】',
   delivery_company     varchar(64),
   delivery_sn          varchar(64) comment 'logistic id',
   auto_confirm_day     int,
   integration          int comment 'member points receivable',
   growth               int,
   bill_type            tinyint ,
   bill_header          varchar(255),
   bill_content         varchar(255),
   bill_receiver_phone  varchar(32),
   bill_receiver_email  varchar(64),
   receiver_name        varchar(100),
   receiver_phone       varchar(32),
   receiver_post_code   varchar(32),
   receiver_state       varchar(32),
   receiver_city        varchar(32),
   receiver_detail_address varchar(200),
   order_note           varchar(500),
   confirm_status       tinyint comment 'delivered[0->no；1->yes]',
   delete_status        tinyint comment '[0->no；1->yes】',
   used_integration     int comment 'member points used',
   payment_time         datetime,
   delivery_time        datetime,
   receive_time         datetime,
   comment_time         datetime,
   modify_time          datetime,
   primary key (id)
);

alter table oms_order;

/*==============================================================*/
/* Table: oms_order_item                                        */
/*==============================================================*/
create table oms_order_item
(
   id                   bigint not null auto_increment,
   order_id             bigint,
   order_sn             char(32),
   spu_id               bigint,
   spu_name             varchar(255),
   spu_pic              varchar(500),
   spu_brand            varchar(200),
   category_id          bigint,
   sku_id               bigint,
   sku_name             varchar(255),
   sku_pic              varchar(500),
   sku_price            decimal(18,4),
   sku_quantity         int,
   sku_attrs_vals       varchar(500) comment 'sku attributes（JSON）',
   promotion_amount     decimal(18,4),
   coupon_amount        decimal(18,4),
   integration_amount   decimal(18,4),
   real_amount          decimal(18,4),
   gift_integration     int,
   gift_growth          int,
   primary key (id)
);

alter table oms_order_item;

/*==============================================================*/
/* Table: oms_order_operate_history                             */
/*==============================================================*/
create table oms_order_operate_history
(
   id                   bigint not null auto_increment,
   order_id             bigint,
   operate_man          varchar(100) comment 'by [member；system；admin]',
   create_time          datetime,
   order_status         tinyint comment '【0->to pay；1->to ship；2->delivered；3->completed；4->closed；5->invalid order】',
   note                 varchar(500),
   primary key (id)
);

alter table oms_order_operate_history;

/*==============================================================*/
/* Table: oms_order_return_apply                                */
/*==============================================================*/
create table oms_order_return_apply
(
   id                   bigint not null auto_increment,
   order_id             bigint,
   sku_id               bigint,
   order_sn             char(32),
   create_time          datetime,
   member_username      varchar(64),
   return_amount        decimal(18,4),
   return_name          varchar(100),
   return_phone         varchar(20),
   status               tinyint(1) comment '[0->pending；1->returning；2->completed；3->rejected]',
   handle_time          datetime,
   sku_img              varchar(500),
   sku_name             varchar(200),
   sku_brand            varchar(200),
   sku_attrs_vals       varchar(500),
   sku_count            int,
   sku_price            decimal(18,4),
   sku_real_price       decimal(18,4),
   reason               varchar(200),
   description          varchar(500),
   desc_pics            varchar(2000),
   handle_note          varchar(500),
   handle_man           varchar(200),
   receive_man          varchar(100),
   receive_time         datetime,
   receive_note         varchar(500),
   receive_phone        varchar(20),
   company_address      varchar(500),
   primary key (id)
);

alter table oms_order_return_apply;

/*==============================================================*/
/* Table: oms_order_return_reason                               */
/*==============================================================*/
create table oms_order_return_reason
(
   id                   bigint not null auto_increment,
   name                 varchar(200) comment 'return reason',
   sort                 int,
   status               tinyint(1),
   create_time          datetime,
   primary key (id)
);

alter table oms_order_return_reason;

/*==============================================================*/
/* Table: oms_order_setting                                     */
/*==============================================================*/
create table oms_order_setting
(
   id                   bigint not null auto_increment,
   flash_order_overtime int comment '(min)',
   normal_order_overtime int comment '(min)',
   confirm_overtime     int comment 'auto confirm received（days）',
   complete_overtime    int comment 'auto set completed（days）',
   comment_overtime     int comment 'auto give good rating（days）',
   member_level         tinyint(2),
   primary key (id)
);

alter table oms_order_setting;

/*==============================================================*/
/* Table: oms_payment_info                                      */
/*==============================================================*/
create table oms_payment_info
(
   id                   bigint not null auto_increment,
   order_sn             char(32),
   order_id             bigint,
   total_amount         decimal(18,4),
   subject              varchar(200) comment 'payment info',
   payment_status       varchar(20),
   create_time          datetime,
   confirm_time         datetime,
   callback_content     varchar(4000),
   callback_time        datetime,
   primary key (id)
);

alter table oms_payment_info;

/*==============================================================*/
/* Table: oms_refund_info                                       */
/*==============================================================*/
create table oms_refund_info
(
   id                   bigint not null auto_increment,
   order_return_id      bigint,
   refund               decimal(18,4),
   refund_sn            varchar(64) comment 'refund id',
   refund_status        tinyint(1),
   refund_channel       tinyint,
   refund_content       varchar(5000),
   primary key (id)
);

alter table oms_refund_info;