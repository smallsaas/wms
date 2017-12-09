CREATE TABLE IF NOT EXISTS t_feedback (
    id BIGINT(20) primary key,
    user_id BIGINT(20)not null,
    deal_user_id BIGINT(20)not null,
    deal_opinion text,
    content text,
    created_date timestamp null,
    unread integer not null default 1
) ENGINE=InnoDB  DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS t_feedback_image (
    id BIGINT(20) primary key,
    feedback_id BIGINT(20) not null,
    url varchar(200)
) ENGINE=InnoDB  DEFAULT CHARSET=UTF8;
