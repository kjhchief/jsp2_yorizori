--#1. ȸ�� ���̺� ����(������)
drop table member;

CREATE TABLE member (
    member_id    VARCHAR2(20),
    password     VARCHAR2(20) NOT NULL,
    name         VARCHAR2(30) NOT NULL,
    email        VARCHAR2(30) ,
    age          NUMBER(3)    NOT NULL,
    regdate      DATE DEFAULT SYSDATE
);


--#2. �������� �߰�
ALTER TABLE member
  ADD ( CONSTRAINT member_id_pk   PRIMARY KEY(member_id),
        CONSTRAINT member_email_uk  UNIQUE (email));

--#3. ������ ���Ǽ��� ���� �������� ��Ȱ��ȭ
ALTER TABLE member
  DISABLE CONSTRAINT member_id_pk CASCADE
  DISABLE CONSTRAINT member_email_uk;

ALTER TABLE member
  ENABLE CONSTRAINT member_id_pk
  ENABLE CONSTRAINT member_email_uk;     

-- ȸ�� ��� �׽�Ʈ
INSERT INTO member(member_id, password, name, email, age)
VALUES ('bangry', '1111', '�����', 'bangry@gmail.com', 30);

INSERT INTO member(member_id, password, name, email, age)
VALUES ('gildong', '1111', 'ȫ�浹', 'gildong@gmail.com', 20);

COMMIT;

-- ��ü ȸ�� ��ȸ �׽�Ʈ
SELECT member_id, name, email, TO_CHAR(regdate, 'yyyy-MM-DD') regdate
FROM member
ORDER BY regdate DESC;

-- ���̵�� ȸ�� ��ȸ(��)
SELECT member_id, name, email, TO_CHAR(regdate, 'yyyy-MM-DD HH24:MI:SS') regdate, age
FROM member
WHERE member_id = 'bangry';

-- ���̵�/��й�ȣ�� ȸ�� ��ȸ(�α���)
SELECT member_id, name, email, TO_CHAR(regdate, 'yyyy-MM-DD HH24:MI:SS') regdate, age
FROM member
WHERE member_id = 'bangry' AND password = '1111';

-- �˻����� ���� ȸ����� ��ȸ
SELECT member_id, name, email, TO_CHAR(regdate, 'yyyy-MM-DD') regdate
FROM member
WHERE member_id = 'bangry' OR name LIKE '%��%';

        
-- ����� ���������� �� ȭ�鿡 �������� ��ϰ��� ������ ���� ����� ��� ��ȯ(����¡ ó��)
SELECT member_id, name, password, email, regdate
FROM ( SELECT CEIL(ROWNUM / 10) page, member_id, name, password, email, regdate
       FROM   ( SELECT member_id, name, password, email, TO_CHAR(regdate, 'YYYY-MM-DD DAY') regdate
                FROM member
                ORDER  BY regdate DESC))
WHERE  page = 1;

-- ����� ����������, ��ȸ ��ϰ���, �˻����� ���� ����� ��� ��ȯ(�˻����� ���� ����¡ ó��)
SELECT member_id, name, password, email, regdate
FROM ( SELECT CEIL(ROWNUM / 10) page, member_id, name, password, email, regdate
       FROM   ( SELECT member_id, name, password, email, TO_CHAR(regdate, 'YYYY-MM-DD DAY') regdate
                FROM member
                WHERE member_id = 'bangry' OR name LIKE '%��%'
                ORDER  BY regdate DESC))
WHERE  page = 1;

-- ��ü ȸ���� ��ȸ
SELECT COUNT(*) count
FROM member;

-- �˻����� ���� ȸ���� ��ȸ - ����¡ ó�� �� �ʿ�
SELECT COUNT(*) count
FROM   member
WHERE member_id = 'bangry' OR name LIKE '%��%';