alter table member
    add column if not exists retired bool not null default false comment '은퇴했는지?';

