-- insert into users (name, username, password)
-- values ('John Doe', 'johndoe@gmail.com', '$2a$12$CcwA8alsqBvvyZgYGS3rQelMybc5IAkZdWHQQwzwfAxEvsvxJfBmK'),
--        ('Mike Smith', 'mikesmith@yahoo.com', '$2a$12$WzOKHzL8ZNguz.fp7o66LuUz/6fCqX8fYxVrSZdjrM.4vqB2deszG')
-- ON CONFLICT (username) DO NOTHING;
--
-- insert into tasks (title, description, status, due_date)
-- values ('Buy cheese', null, 'TODO', '2023-01-29 12:00:00'),
--        ('Do homework', 'Math, Physics, Literature', 'IN_PROGRESS', '2023-01-31 00:00:00'),
--        ('Clean rooms', null, 'DONE', null),
--        ('Call Milf', 'Ask about meeting', 'TODO', '2023-02-01 00:00:00');
--
--
-- insert into users_tasks (task_id, user_id)
-- values (1, 2),
--        (2, 2),
--        (3, 2),
--        (4, 1);
--
-- insert into users_roles (user_id, role)
-- values (1, 'ROLE_ADMIN'),
--        (1, 'ROLE_USER'),
--        (2,'ROLE_USER')