ALTER TABLE CTLM_MAIL
   ADD CONSTRAINT PK_CTLM_MAIL PRIMARY KEY (ACTION_DETAIL_ID,ATTACH_SYSOUT,DEST,MESSAGE,SUBJECT)
   USING INDEX
   NOVALIDATE;

ALTER TABLE CTLM_MAIL MODIFY (ATTACH_SYSOUT       NOT NULL ENABLE);
ALTER TABLE CTLM_MAIL MODIFY (DEST                NOT NULL ENABLE);
ALTER TABLE CTLM_MAIL MODIFY (MESSAGE             NOT NULL ENABLE);
ALTER TABLE CTLM_MAIL MODIFY (SUBJECT             NOT NULL ENABLE);
ALTER TABLE CTLM_MAIL MODIFY (URGENCY             NOT NULL ENABLE);
ALTER TABLE CTLM_MAIL MODIFY (CREATED_TSMP        NOT NULL ENABLE);
ALTER TABLE CTLM_MAIL MODIFY (CREATED_BY_NAME     NOT NULL ENABLE);

