CREATE SCHEMA seata_account;

CREATE TABLE seata_account.account_tbl (
  id int(11) NOT NULL AUTO_INCREMENT,
  user_id varchar(255) DEFAULT NULL,
  money int(11) DEFAULT '0',
  PRIMARY KEY (id),
  UNIQUE KEY user_id (user_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO seata_account.account_tbl (id, user_id, money) VALUES ('1', '10001', '100');

CREATE TABLE seata_account.undo_log (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  branch_id bigint(20) NOT NULL,
  xid varchar(100) NOT NULL,
  context varchar(128) NOT NULL,
  rollback_info longblob NOT NULL,
  log_status int(11) NOT NULL,
  log_created datetime NOT NULL,
  log_modified datetime NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY ux_undo_log (xid,branch_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE SCHEMA seata_storage;

CREATE TABLE seata_storage.storage_tbl (
  id int(11) NOT NULL AUTO_INCREMENT,
  commodity_code varchar(255) DEFAULT NULL,
  count int(11) DEFAULT '0',
  PRIMARY KEY (id),
  UNIQUE KEY commodity_code (commodity_code)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO seata_storage.storage_tbl (id, commodity_code, count) VALUES ('1', '101', '5');

CREATE TABLE seata_storage.undo_log (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  branch_id bigint(20) NOT NULL,
  xid varchar(100) NOT NULL,
  context varchar(128) NOT NULL,
  rollback_info longblob NOT NULL,
  log_status int(11) NOT NULL,
  log_created datetime NOT NULL,
  log_modified datetime NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY ux_undo_log (xid,branch_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE SCHEMA seata_order;

CREATE TABLE seata_order.order_tbl (
  id int(11) NOT NULL AUTO_INCREMENT,
  user_id varchar(255) DEFAULT NULL,
  commodity_code varchar(255) DEFAULT NULL,
  count int(11) DEFAULT '0',
  money int(11) DEFAULT '0',
  status int(11) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE seata_order.undo_log (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  branch_id bigint(20) NOT NULL,
  xid varchar(100) NOT NULL,
  context varchar(128) NOT NULL,
  rollback_info longblob NOT NULL,
  log_status int(11) NOT NULL,
  log_created datetime NOT NULL,
  log_modified datetime NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY ux_undo_log (xid,branch_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;