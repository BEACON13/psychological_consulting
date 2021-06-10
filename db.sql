/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/6/10 14:09:20                           */
/*==============================================================*/


drop table if exists add_consult;

drop table if exists closing_report;

drop table if exists consult_apply;

drop table if exists consult_appointment_record;

drop table if exists consult_appointment_report;

drop table if exists consultant_duty;

drop table if exists first_application;

drop table if exists first_visit_record;

drop table if exists first_visit_report;

drop table if exists first_visitor_duty;

drop table if exists location;

drop table if exists person;

drop table if exists person_type;

drop table if exists student;

drop table if exists time_period;

/*==============================================================*/
/* Table: add_consult                                           */
/*==============================================================*/
create table add_consult
(
   add_c_id             bigint not null,
   s_id                 bigint not null,
   c_id                 bigint,
   times                int not null,
   primary key (add_c_id)
);

/*==============================================================*/
/* Table: closing_report                                        */
/*==============================================================*/
create table closing_report
(
   closing_report_id    bigint not null,
   s_id                 bigint not null,
   c_id                 bigint,
   problem_type         varchar(50),
   consult_num          int,
   consult_effect_self  varchar(500),
   primary key (closing_report_id)
);

/*==============================================================*/
/* Table: consult_apply                                         */
/*==============================================================*/
create table consult_apply
(
   consult_apply_id     bigint not null,
   s_id                 bigint not null,
   tp_id_1              smallint,
   tp_id_2              smallint,
   tp_id_3              smallint,
   num                  int,
   primary key (consult_apply_id)
);

/*==============================================================*/
/* Table: consult_appointment_record                            */
/*==============================================================*/
create table consult_appointment_record
(
   consult_appoint_id   bigint not null,
   s_id                 bigint not null,
   tp_id                smallint,
   location_id          bigint,
   c_id                 bigint,
   is_deleted           bool,
   date                 date,
   primary key (consult_appoint_id)
);

/*==============================================================*/
/* Table: consult_appointment_report                            */
/*==============================================================*/
create table consult_appointment_report
(
   car_id               bigint not null,
   s_id                 bigint,
   tp_id                smallint,
   c_id                 bigint,
   consult_result       varchar(20),
   date                 date,
   primary key (car_id)
);

/*==============================================================*/
/* Table: consultant_duty                                       */
/*==============================================================*/
create table consultant_duty
(
   cd_id                bigint not null,
   tp_id                smallint not null,
   location_id          bigint not null,
   c_id                 bigint,
   free_time            date,
   primary key (cd_id)
);

/*==============================================================*/
/* Table: first_application                                     */
/*==============================================================*/
create table first_application
(
   fva_id               bigint not null,
   s_id                 bigint not null,
   tp_id                smallint,
   score                int,
   name                 varchar(50),
   phone                varchar(11),
   address              varchar(100),
   emergency_phone      varchar(11),
   physical_illness     varchar(200),
   is_diagnosed         bool,
   emergency_level      varchar(10),
   problem_type         varchar(50),
   consult_expectation  varchar(100),
   consult_history      varchar(200),
   primary key (fva_id)
);

/*==============================================================*/
/* Table: first_visit_record                                    */
/*==============================================================*/
create table first_visit_record
(
   fvr_id               bigint not null,
   s_id                 bigint not null,
   tp_id                smallint,
   location_id          bigint,
   fv_id                bigint,
   date                 date,
   is_deleted           bool,
   primary key (fvr_id)
);

/*==============================================================*/
/* Table: first_visit_report                                    */
/*==============================================================*/
create table first_visit_report
(
   fvreport_id          bigint not null,
   s_id                 bigint,
   tp_id                smallint,
   fv_id                bigint,
   danger_level         varchar(50),
   problem_type         varchar(50),
   conclusion           varchar(50),
   date                 date,
   primary key (fvreport_id)
);

/*==============================================================*/
/* Table: first_visitor_duty                                    */
/*==============================================================*/
create table first_visitor_duty
(
   fvd_id               bigint not null,
   tp_id                smallint not null,
   location_id          bigint not null,
   fv_id                bigint,
   primary key (fvd_id)
);

/*==============================================================*/
/* Table: location                                              */
/*==============================================================*/
create table location
(
   location_id          bigint not null,
   location_type        int not null,
   location_name        varchar(50) not null,
   primary key (location_id)
);

/*==============================================================*/
/* Table: person                                                */
/*==============================================================*/
create table person
(
   p_id                 bigint not null,
   username             varchar(20) not null,
   password             varchar(20) not null,
   name                 varchar(50),
   phone                varchar(11),
   gender               varchar(2),
   job                  varchar(20),
   age                  smallint,
   info                 varchar(1024),
   address              varchar(100),
   email                varchar(50),
   primary key (p_id)
);

/*==============================================================*/
/* Table: person_type                                           */
/*==============================================================*/
create table person_type
(
   pt_id                bigint not null,
   p_id                 bigint not null,
   type                 varchar(20),
   primary key (pt_id)
);

/*==============================================================*/
/* Table: student                                               */
/*==============================================================*/
create table student
(
   s_id                 bigint not null,
   code                 varchar(20),
   name                 varchar(50),
   phone                varchar(11),
   college              varchar(50),
   grade                int,
   gender               varchar(2),
   birth_date           date,
   is_qualified         bool,
   password             varchar(20),
   primary key (s_id)
);

alter table student comment '学生表';

/*==============================================================*/
/* Table: time_period                                           */
/*==============================================================*/
create table time_period
(
   tp_id                smallint not null,
   start_time           time,
   duration             int,
   weekday              smallint,
   primary key (tp_id)
);

alter table add_consult add constraint FK_Reference_35 foreign key (c_id)
      references person (p_id) on delete restrict on update restrict;

alter table add_consult add constraint FK_stu_add_consult foreign key (s_id)
      references student (s_id) on delete restrict on update restrict;

alter table closing_report add constraint FK_Reference_34 foreign key (c_id)
      references person (p_id) on delete restrict on update restrict;

alter table closing_report add constraint FK_stu_closing_report foreign key (s_id)
      references student (s_id) on delete restrict on update restrict;

alter table consult_apply add constraint FK_Reference_42 foreign key (tp_id_1)
      references time_period (tp_id) on delete restrict on update restrict;

alter table consult_apply add constraint FK_Reference_43 foreign key (tp_id_2)
      references time_period (tp_id) on delete restrict on update restrict;

alter table consult_apply add constraint FK_Reference_44 foreign key (tp_id_3)
      references time_period (tp_id) on delete restrict on update restrict;

alter table consult_apply add constraint FK_stu_consult_apply foreign key (s_id)
      references student (s_id) on delete restrict on update restrict;

alter table consult_appointment_record add constraint FK_Reference_27 foreign key (c_id)
      references person (p_id) on delete restrict on update restrict;

alter table consult_appointment_record add constraint FK_Reference_28 foreign key (tp_id)
      references time_period (tp_id) on delete restrict on update restrict;

alter table consult_appointment_record add constraint FK_Reference_29 foreign key (location_id)
      references location (location_id) on delete restrict on update restrict;

alter table consult_appointment_record add constraint FK_stu_consult_appoint foreign key (s_id)
      references student (s_id) on delete restrict on update restrict;

alter table consult_appointment_report add constraint FK_Reference_39 foreign key (s_id)
      references student (s_id) on delete restrict on update restrict;

alter table consult_appointment_report add constraint FK_Reference_40 foreign key (tp_id)
      references time_period (tp_id) on delete restrict on update restrict;

alter table consult_appointment_report add constraint FK_Reference_41 foreign key (c_id)
      references person (p_id) on delete restrict on update restrict;

alter table consultant_duty add constraint FK_Reference_33 foreign key (c_id)
      references person (p_id) on delete restrict on update restrict;

alter table consultant_duty add constraint FK_consultant_loc foreign key (location_id)
      references location (location_id) on delete restrict on update restrict;

alter table consultant_duty add constraint FK_time_cd foreign key (tp_id)
      references time_period (tp_id) on delete restrict on update restrict;

alter table first_application add constraint FK_Reference_26 foreign key (tp_id)
      references time_period (tp_id) on delete restrict on update restrict;

alter table first_application add constraint FK_stu_first_application2 foreign key (s_id)
      references student (s_id) on delete restrict on update restrict;

alter table first_visit_record add constraint FK_Reference_24 foreign key (tp_id)
      references time_period (tp_id) on delete restrict on update restrict;

alter table first_visit_record add constraint FK_Reference_25 foreign key (location_id)
      references location (location_id) on delete restrict on update restrict;

alter table first_visit_record add constraint FK_Reference_32 foreign key (fv_id)
      references person (p_id) on delete restrict on update restrict;

alter table first_visit_record add constraint FK_stu_fvr foreign key (s_id)
      references student (s_id) on delete restrict on update restrict;

alter table first_visit_report add constraint FK_Reference_36 foreign key (s_id)
      references student (s_id) on delete restrict on update restrict;

alter table first_visit_report add constraint FK_Reference_37 foreign key (tp_id)
      references time_period (tp_id) on delete restrict on update restrict;

alter table first_visit_report add constraint FK_Reference_38 foreign key (fv_id)
      references person (p_id) on delete restrict on update restrict;

alter table first_visitor_duty add constraint FK_Reference_31 foreign key (fv_id)
      references person (p_id) on delete restrict on update restrict;

alter table first_visitor_duty add constraint FK_fv_loc foreign key (location_id)
      references location (location_id) on delete restrict on update restrict;

alter table first_visitor_duty add constraint FK_fvd_time foreign key (tp_id)
      references time_period (tp_id) on delete restrict on update restrict;

alter table person_type add constraint FK_Reference_30 foreign key (p_id)
      references person (p_id) on delete restrict on update restrict;

