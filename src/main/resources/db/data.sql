insert into user_tb(username, password, salt, email, address, detail_address, tel, role, created_at) values('ssar', '7c3fd9ebc3c42a298f6e1a9fc3437ac5994b829c6d5dffb44e22e25023f4049f', 'PlaqMgXB8zGtrsD2CfZEcg==', 'kimalss22@gmail.com', '부산시 야호구 야동','11길','01023452344', 'employee', now());
insert into user_tb(username, password, salt, email, address, detail_address, tel, role, created_at) values('love', '7c3fd9ebc3c42a298f6e1a9fc3437ac5994b829c6d5dffb44e22e25023f4049f', 'PlaqMgXB8zGtrsD2CfZEcg==', 'love@nate.com', '부산시 야호구 상동','12길','01011111111', 'employee', now());
insert into user_tb(username, password, salt, email, address, detail_address, tel, role, created_at) values('google', '7c3fd9ebc3c42a298f6e1a9fc3437ac5994b829c6d5dffb44e22e25023f4049f', 'PlaqMgXB8zGtrsD2CfZEcg==', 'google@nate.com', '부산시 야구 김동','13길','01012331233', 'employee', now());
insert into user_tb(username, password, salt, email, address, detail_address, tel, role, created_at) values('naver', '7c3fd9ebc3c42a298f6e1a9fc3437ac5994b829c6d5dffb44e22e25023f4049f', 'PlaqMgXB8zGtrsD2CfZEcg==', 'naver@nate.com', '부산시 박박구 쿠동','14길','01034566666',  'employee', now());
insert into user_tb(username, password, salt, email, address, detail_address, tel, role, created_at) values('noew', '7c3fd9ebc3c42a298f6e1a9fc3437ac5994b829c6d5dffb44e22e25023f4049f', 'PlaqMgXB8zGtrsD2CfZEcg==', 'noew@nate.com', '부산시 상상구 모동','15길','01066666666',  'employee', now());
insert into user_tb(username, password, salt, email, address, detail_address, tel, role, created_at) values('cos', '7c3fd9ebc3c42a298f6e1a9fc3437ac5994b829c6d5dffb44e22e25023f4049f', 'PlaqMgXB8zGtrsD2CfZEcg==', 'cos@nate.com', '부산시 빡구 동동','71길','01012341234',  'company', now());
insert into user_tb(username, password, salt, email, address, detail_address, tel, role, created_at) values('kit', '7c3fd9ebc3c42a298f6e1a9fc3437ac5994b829c6d5dffb44e22e25023f4049f', 'PlaqMgXB8zGtrsD2CfZEcg==', 'kit@nate.com', '부산시 봉구 원동','72길','01032113211',  'company', now());

insert into employee_tb(user_id, real_name, career) values(1, '김살', 1);
insert into employee_tb(user_id, real_name, career) values(2, '박사랑', 0);
insert into employee_tb(user_id, real_name, career) values(3, '제갈구글', 3);
insert into employee_tb(user_id, real_name, career) values(4, '독고네이버', 5);
insert into employee_tb(user_id, real_name, career) values(5, '상냥이', 6);

insert into resume_tb(user_id, title, content, created_at) values(1,'이력제 제목1','이력서 내용1', now());
insert into resume_tb(user_id, title, content, created_at) values(2,'이력제 제목2','이력서 내용2', now());
insert into resume_tb(user_id, title, content, created_at) values(3,'이력제 제목3','이력서 내용3', now());
insert into resume_tb(user_id, title, content, created_at) values(4,'이력제 제목4','이력서 내용4', now());
insert into resume_tb(user_id, title, content, created_at) values(5,'이력제 제목5','이력서 내용5', now());

insert into company_tb(user_id, company_name, company_scale, company_numb, company_field) values(6, '(주)미친회사', '대기업', 333322221111, 'IT업');
insert into company_tb(user_id, company_name, company_scale, company_numb, company_field) values(7, '무식한형제들', '중견기업', 111122223333, 'IT업');

insert into board_tb(user_id, title, content, career, job_type, education, favor, deadline, created_at) values(6, '공고제목1','공고내용1', 1, 1, 1, '프로젝트 유경험자', '2023-05-09', now());
insert into board_tb(user_id, title, content, career, job_type, education, favor, deadline, created_at) values(6, '공고제목2','공고내용2', 3, 1, 2, '프로젝트 유경험자', '2023-03-12', now());
insert into board_tb(user_id, title, content, career, job_type, education, favor, deadline, created_at) values(6, '공고제목3','공고내용3', 6, 2, 4, '관련 자격증 보유자', '2023-04-15', now());
insert into board_tb(user_id, title, content, career, job_type, education, favor, deadline, created_at) values(6, '공고제목4','공고내용4', 1, 2, 2, '프로젝트 유경험자', '2023-06-01', now());
insert into board_tb(user_id, title, content, career, job_type, education, favor, deadline, created_at) values(7, '공고제목5','공고내용4', 3, 1, 1, '관련 자격증 보유자', '2023-03-04', now());
insert into board_tb(user_id, title, content, career, job_type, education, favor, deadline, created_at) values(7, '공고제목6','공고내용4', 6, 1, 2, '프로젝트 유경험자', '2023-03-06', now());
insert into board_tb(user_id, title, content, career, job_type, education, favor, deadline, created_at) values(7, '공고제목7','공고내용4', 6, 1, 2, '프로젝트 유경험자', '2023-05-01', now());
insert into board_tb(user_id, title, content, career, job_type, education, favor, deadline, created_at) values(7, '공고제목8','공고내용4', 1, 2, 4, '관련 자격증 보유자', '2023-04-27', now());
insert into board_tb(user_id, title, content, career, job_type, education, favor, deadline, created_at) values(7, '공고제목9','공고내용4', 1, 2, 4, '관련 자격증 보유자', '2023-05-11', now());
insert into board_tb(user_id, title, content, career, job_type, education, favor, deadline, created_at) values(7, '공고제목10','공고내용4', 1, 2, 4, '관련 자격증 보유자', '2023-05-15', now());
insert into board_tb(user_id, title, content, career, job_type, education, favor, deadline, created_at) values(7, '공고제목11','공고내용4', 1, 2, 4, '관련 자격증 보유자', '2023-03-20', now());
insert into board_tb(user_id, title, content, career, job_type, education, favor, deadline, created_at) values(7, '공고제목12','공고내용4', 1, 2, 4, '관련 자격증 보유자', '2023-06-05', now());
insert into board_tb(user_id, title, content, career, job_type, education, favor, deadline, created_at) values(7, '공고제목13','공고내용4', 1, 2, 4, '관련 자격증 보유자', '2023-03-19', now());
insert into board_tb(user_id, title, content, career, job_type, education, favor, deadline, created_at) values(7, '공고제목14','공고내용4', 1, 2, 4, '관련 자격증 보유자', '2023-04-21', now());
insert into board_tb(user_id, title, content, career, job_type, education, favor, deadline, created_at) values(7, '공고제목15','공고내용4', 1, 2, 4, '관련 자격증 보유자', '2023-03-29', now());
insert into board_tb(user_id, title, content, career, job_type, education, favor, deadline, created_at) values(7, '공고제목16','공고내용4', 1, 2, 4, '관련 자격증 보유자', '2023-06-07', now());
insert into board_tb(user_id, title, content, career, job_type, education, favor, deadline, created_at) values(7, '공고제목17','공고내용4', 1, 2, 4, '관련 자격증 보유자', '2023-03-16', now());
insert into board_tb(user_id, title, content, career, job_type, education, favor, deadline, created_at) values(7, '공고제목18','공고내용4', 1, 2, 4, '관련 자격증 보유자', '2023-03-27', now());

insert into apply_tb(user_id, board_id, resume_id, state, created_at) values(1, 1, 1, -1, now());
insert into apply_tb(user_id, board_id, resume_id, state, created_at) values(2, 1, 2, 1, now());
insert into apply_tb(user_id, board_id, resume_id, state, created_at) values(1, 2, 1, 0, now());
insert into apply_tb(user_id, board_id, resume_id, state, created_at) values(2, 2, 2, 0, now());

insert into skill_tb(skill_code, skill_name) values(1, 'JAVA');
insert into skill_tb(skill_code, skill_name) values(2, 'C');
insert into skill_tb(skill_code, skill_name) values(3, 'Python');
insert into skill_tb(skill_code, skill_name) values(4, 'PHP');
insert into skill_tb(skill_code, skill_name) values(5, 'JS');
insert into skill_tb(skill_code, skill_name) values(6, 'Ruby');
insert into skill_tb(skill_code, skill_name) values(7, 'Assembly');
insert into skill_tb(skill_code, skill_name) values(8, 'MYSQL');

insert into employeetech_tb(user_id, skill) values(1, 1);
insert into employeetech_tb(user_id, skill) values(1, 2);
insert into employeetech_tb(user_id, skill) values(1, 5);
insert into employeetech_tb(user_id, skill) values(2, 2);
insert into employeetech_tb(user_id, skill) values(2, 4);
insert into employeetech_tb(user_id, skill) values(2, 7);

insert into boardtech_tb(board_id, skill) values(1, 2);
insert into boardtech_tb(board_id, skill) values(1, 3);
insert into boardtech_tb(board_id, skill) values(1, 4);
insert into boardtech_tb(board_id, skill) values(2, 6);
insert into boardtech_tb(board_id, skill) values(2, 4);
insert into boardtech_tb(board_id, skill) values(2, 7);
insert into boardtech_tb(board_id, skill) values(4, 1);
insert into boardtech_tb(board_id, skill) values(5, 3);
insert into boardtech_tb(board_id, skill) values(6, 1);
insert into boardtech_tb(board_id, skill) values(7, 3);
insert into boardtech_tb(board_id, skill) values(8, 1);
insert into boardtech_tb(board_id, skill) values(9, 3);
insert into boardtech_tb(board_id, skill) values(10, 1);
insert into boardtech_tb(board_id, skill) values(11, 3);
insert into boardtech_tb(board_id, skill) values(12, 1);
insert into boardtech_tb(board_id, skill) values(13, 3);
insert into boardtech_tb(board_id, skill) values(14, 1);
insert into boardtech_tb(board_id, skill) values(15, 2);
insert into boardtech_tb(board_id, skill) values(16, 1);
insert into boardtech_tb(board_id, skill) values(17, 1);

insert into love_tb(id, board_id, user_id, created_at) values(1, 1, 1, now());
insert into love_tb(id, board_id, user_id, created_at) values(2, 4, 1, now());

commit; 