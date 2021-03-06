ALTER TABLE CTLM_C_CONDITION
   ADD CONSTRAINT PK_CTLM_C_CONDITION PRIMARY KEY (UUID)
   USING INDEX
   NOVALIDATE;

ALTER TABLE CTLM_C_CONDITION MODIFY (JOB_NAME            NOT NULL ENABLE);
ALTER TABLE CTLM_C_CONDITION MODIFY (NAME                NOT NULL ENABLE);
ALTER TABLE CTLM_C_CONDITION MODIFY (ODATE               NOT NULL ENABLE);
ALTER TABLE CTLM_C_CONDITION MODIFY (CREATED_TSMP        NOT NULL ENABLE);
ALTER TABLE CTLM_C_CONDITION MODIFY (CREATED_BY_NAME     NOT NULL ENABLE);
