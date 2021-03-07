create table activities (id bigint auto_increment,
  start_time datetime,
  activity_desc text,
  activity_type ENUM('BIKING', 'HIKING', 'RUNNING', 'BASKETBALL')
  , constraint pk_activities primary key(id));