CREATE TABLE role
(
    role_id         INT AUTO_INCREMENT NOT NULL,
    name            VARCHAR(255)       NOT NULL,
    description     TEXT               NOT NULL,
    hierarchy_level INT                NOT NULL,
    PRIMARY KEY (role_id)
);

CREATE TABLE person
(
    person_id     INT AUTO_INCREMENT NOT NULL,
    first_name    VARCHAR(255)       NOT NULL,
    last_name     VARCHAR(255)       NOT NULL,
    date_of_birth DATE               NOT NULL,
    email         VARCHAR(255)       NOT NULL UNIQUE,
    phone_number  VARCHAR(255)       NOT NULL UNIQUE,
    PRIMARY KEY (person_id)
);

CREATE TABLE employee
(
    employee_id INT AUTO_INCREMENT NOT NULL,
    hiring_date DATE               NOT NULL,
    salary      INT                NOT NULL,
    password    VARCHAR(255)       NOT NULL,
    person_id   INT                NOT NULL,
    role_id     INT                NOT NULL,
    PRIMARY KEY (employee_id),
    FOREIGN KEY (person_id) REFERENCES person (person_id),
    FOREIGN KEY (role_id) REFERENCES role (role_id)
);

CREATE TABLE dinner
(
    dinner_id    INT AUTO_INCREMENT NOT NULL,
    name         VARCHAR(255)       NOT NULL,
    description  TEXT               NOT NULL,
    time_to_make TIME               NOT NULL,
    type         VARCHAR(255)       NOT NULL,
    price        INT                NOT NULL,
    PRIMARY KEY (dinner_id)
);

CREATE TABLE lunch
(
    lunch_id INT AUTO_INCREMENT NOT NULL,
    price    INT                NOT NULL,
    date     DATE               NOT NULL,
    PRIMARY KEY (lunch_id)
);

CREATE TABLE tables
(
    table_number    INT NOT NULL,
    room_for_people INT NOT NULL,
    PRIMARY KEY (table_number)
);

CREATE TABLE shift
(
    schedule_id INT AUTO_INCREMENT NOT NULL,
    employee_id INT                NOT NULL,
    shift_date  DATE               NOT NULL,
    start_time  TIME               NOT NULL,
    end_time    TIME               NOT NULL,
    PRIMARY KEY (schedule_id),
    UNIQUE (employee_id)
);

CREATE TABLE bookings
(
    booking_id   INT AUTO_INCREMENT              NOT NULL,
    date         DATE                            NOT NULL,
    start_time   TIME                            NOT NULL,
    end_time     TIME                            NOT NULL,
    table_number INT                             NOT NULL,
    status       ENUM ('Confirmed', 'Cancelled') NOT NULL DEFAULT 'Confirmed',
    person_id    INT                             NOT NULL,
    PRIMARY KEY (booking_id),
    FOREIGN KEY (table_number) REFERENCES tables (table_number),
    FOREIGN KEY (person_id) REFERENCES person (person_id)
);

CREATE TABLE events
(
    event_id    INT AUTO_INCREMENT NOT NULL,
    name        VARCHAR(255)       NOT NULL,
    description TEXT               NOT NULL,
    date        DATE               NOT NULL,
    start_time  TIME               NOT NULL,
    end_time    TIME               NOT NULL,
    PRIMARY KEY (event_id)
);

CREATE TABLE tab
(
    tab_id          INT AUTO_INCREMENT      NOT NULL,
    opened_at       DATETIME                NOT NULL,
    closed_at       DATETIME,
    status          ENUM ('Open', 'Closed') NOT NULL DEFAULT 'Open',
    last_updated_at DATETIME                NOT NULL,
    employee_id     INT                     NOT NULL,
    table_number    INT                     NOT NULL,
    PRIMARY KEY (tab_id),
    FOREIGN KEY (employee_id) REFERENCES employee (employee_id),
    FOREIGN KEY (table_number) REFERENCES tables (table_number)
);

CREATE TABLE drinks
(
    drink_id    INT AUTO_INCREMENT NOT NULL,
    name        VARCHAR(255)       NOT NULL,
    type        VARCHAR(255)       NOT NULL,
    description TEXT               NOT NULL,
    price       INT                NOT NULL,
    PRIMARY KEY (drink_id)
);

CREATE TABLE items
(
    item_id     INT AUTO_INCREMENT NOT NULL,
    name        VARCHAR(255)       NOT NULL,
    description TEXT               NOT NULL,
    PRIMARY KEY (item_id)
);

CREATE TABLE works_shift
(
    schedule_id INT NOT NULL,
    employee_id INT NOT NULL,
    PRIMARY KEY (schedule_id, employee_id),
    FOREIGN KEY (schedule_id) REFERENCES shift (schedule_id),
    FOREIGN KEY (employee_id) REFERENCES employee (employee_id)
);

CREATE TABLE orders
(
    order_id     INT AUTO_INCREMENT                    NOT NULL,
    date         DATE                                  NOT NULL,
    status       ENUM ('Waiting', 'Preparing', 'Done') NOT NULL DEFAULT 'Waiting',
    employee_id  INT                                   NOT NULL,
    table_number INT                                   NOT NULL,
    tab_id       INT                                   NOT NULL,
    note         TEXT,
    PRIMARY KEY (order_id),
    FOREIGN KEY (employee_id) REFERENCES employee (employee_id),
    FOREIGN KEY (table_number) REFERENCES tables (table_number),
    FOREIGN KEY (tab_id) REFERENCES tab (tab_id)
);

CREATE TABLE food_order
(
    id        INT AUTO_INCREMENT NOT NULL,
    dinner_id INT                NOT NULL,
    order_id  INT                NOT NULL,
    note      TEXT,
    PRIMARY KEY (id),
    FOREIGN KEY (dinner_id) REFERENCES dinner (dinner_id),
    FOREIGN KEY (order_id) REFERENCES orders (order_id)
);

CREATE TABLE drink_order
(
    id       INT AUTO_INCREMENT NOT NULL,
    drink_id INT                NOT NULL,
    order_id INT                NOT NULL,
    quantity INT                NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    FOREIGN KEY (drink_id) REFERENCES drinks (drink_id),
    FOREIGN KEY (order_id) REFERENCES orders (order_id)
);

CREATE TABLE lunch_items
(
    id         INT AUTO_INCREMENT NOT NULL,
    lunch_id   INT                NOT NULL,
    item_id    INT                NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (lunch_id) REFERENCES lunch (lunch_id),
    FOREIGN KEY (item_id) REFERENCES items (item_id)
);