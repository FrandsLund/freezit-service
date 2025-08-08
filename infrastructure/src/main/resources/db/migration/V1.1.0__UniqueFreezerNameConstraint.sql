ALTER TABLE freezerentity
ADD CONSTRAINT unique_user_freezer_name UNIQUE (userId, name);