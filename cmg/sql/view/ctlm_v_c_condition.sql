CREATE OR REPLACE VIEW CTLM_V_C_CONDITION
(
  JOB_NAME
, ACTION_DETAIL_ID
, CONDITION_NAME
, AND_OR
, ODATE
, SIGN
)
AS
SELECT
  JOB_NAME
, ACTION_DETAIL_ID
, NAME
, AND_OR
, ODATE
, SIGN
FROM CTLM_C_CONDITION
;
