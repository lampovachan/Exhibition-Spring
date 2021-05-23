DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS USER_ROLES;
DROP TABLE IF EXISTS CHOICE_MUSEUM;
DROP TABLE IF EXISTS MUSEUM;
DROP TABLE IF EXISTS EXHIBITION;

CREATE TABLE CHOICE_MUSEUM (
                                   ID            BIGINT                  NOT NULL,
                                   CHOICE_DATE   TIMESTAMP DEFAULT NOW() NOT NULL,
                                   MUSEUM_ID     BIGINT                  NOT NULL,
                                   USER_ID       BIGINT                  NOT NULL
);
ALTER TABLE CHOICE_MUSEUM ADD CONSTRAINT CHOICE_RESTAURANT_ID PRIMARY KEY (ID);
ALTER TABLE CHOICE_MUSEUM ADD CONSTRAINT UNIQUE_USER_CHOICE_DATE_IDX UNIQUE(USER_ID, CHOICE_DATE);

CREATE TABLE EXHIBITION (
                       ID                 BIGINT NOT NULL,
                       EXHIBITION_DATE    DATE  NOT NULL,
                       NAME               VARCHAR(100),
                       PRICE              INTEGER NOT NULL,
                       MUSEUM_ID          BIGINT NOT NULL
);
ALTER TABLE EXHIBITION ADD CONSTRAINT EXHIBITION_ID PRIMARY KEY (ID);
ALTER TABLE EXHIBITION ADD CONSTRAINT UNIQUE_RESTAURANT_ID_NAME_IDX UNIQUE(MUSEUM_ID, NAME);

CREATE TABLE MUSEUM (
                            ID   BIGINT NOT NULL,
                            NAME VARCHAR(100)
);
ALTER TABLE MUSEUM ADD CONSTRAINT RESTAURANT_ID PRIMARY KEY (ID);
ALTER TABLE MUSEUM ADD CONSTRAINT UNIQUE_MUSEUM_NAME_IDX UNIQUE(NAME);


CREATE TABLE USER_ROLES (
                            USER_ID BIGINT NOT NULL,
                            ROLE    VARCHAR(255)
);

CREATE TABLE USERS (
                       ID         BIGINT                  NOT NULL,
                       NAME       VARCHAR(100),
                       EMAIL      VARCHAR(100)            NOT NULL,
                       ENABLED    BOOL DEFAULT TRUE       NOT NULL,
                       PASSWORD   VARCHAR(100)            NOT NULL,
                       REGISTERED TIMESTAMP DEFAULT NOW() NOT NULL
);
ALTER TABLE USERS ADD CONSTRAINT USERS_ID PRIMARY KEY (ID);
ALTER TABLE USERS ADD CONSTRAINT UNIQUE_USERS_EMAIL_IDX UNIQUE(EMAIL);

ALTER TABLE EXHIBITION ADD CONSTRAINT FK_EXHIBITION_MUSEUM_IDX FOREIGN KEY(MUSEUM_ID) REFERENCES MUSEUM(ID) NOCHECK;
ALTER TABLE USER_ROLES ADD CONSTRAINT FK_USER_ROLES_USERS_IDX FOREIGN KEY(USER_ID) REFERENCES USERS(ID) NOCHECK;
ALTER TABLE CHOICE_MUSEUM ADD CONSTRAINT FK_CHOICE_MUSEUM_USERS_IDX FOREIGN KEY(USER_ID) REFERENCES USERS(ID) NOCHECK;
ALTER TABLE CHOICE_MUSEUM ADD CONSTRAINT FK_CHOICE_MUSEUM_IDX FOREIGN KEY(MUSEUM_ID) REFERENCES MUSEUM(ID) NOCHECK;