CREATE OR REPLACE VIEW CTLM_V_FORCE_JOB
(
  ACTION_DETAIL_ID
, NAME
, ODATE
, TABLE_NAME
)
AS
SELECT
  ACTION_DETAIL_ID
, NAME
, ODATE
, TABLE_NAME
FROM CTLM_FORCE_JOB
;