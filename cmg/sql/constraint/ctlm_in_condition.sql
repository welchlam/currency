ALTER TABLE CTLM_IN_CONDITION
   ADD CONSTRAINT PK_CTLM_IN_CONDITION PRIMARY KEY (JOB_NAME,PARENT_JOB,CHILD_JOB)
   USING INDEX
   NOVALIDATE;

ALTER TABLE CTLM_IN_CONDITION MODIFY (JOB_NAME            NOT NULL ENABLE);
ALTER TABLE CTLM_IN_CONDITION MODIFY (PARENT_JOB          NOT NULL ENABLE);
ALTER TABLE CTLM_IN_CONDITION MODIFY (CHILD_JOB           NOT NULL ENABLE);
ALTER TABLE CTLM_IN_CONDITION MODIFY (AND_OR              NOT NULL ENABLE);
ALTER TABLE CTLM_IN_CONDITION MODIFY (ODATE               NOT NULL ENABLE);
ALTER TABLE CTLM_IN_CONDITION MODIFY (CREATED_TSMP        NOT NULL ENABLE);
ALTER TABLE CTLM_IN_CONDITION MODIFY (CREATED_BY_NAME     NOT NULL ENABLE);