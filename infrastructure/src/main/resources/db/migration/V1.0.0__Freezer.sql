CREATE TABLE freezerentity (
	userid int4 NOT NULL,
	freezerid uuid NOT NULL,
	name varchar(255) NOT NULL,
	CONSTRAINT freezerentity_pkey PRIMARY KEY (freezerid)
);

CREATE TABLE shelfentity (
	shelfnumber int4 NOT NULL,
	freezerentity_freezerid uuid NULL,
	shelfid uuid NOT NULL,
	CONSTRAINT shelfentity_pkey PRIMARY KEY (shelfid),
	CONSTRAINT FK_shelfentity_freezerentity FOREIGN KEY (freezerentity_freezerid) REFERENCES public.freezerentity(freezerid)
);

CREATE TABLE freezeritementity (
	quantity int4 NOT NULL,
	dateadded timestamptz(6) NOT NULL,
	freezeritemid uuid NOT NULL,
	shelfentity_shelfid uuid NULL,
	description varchar(255) NULL,
	name varchar(255) NOT NULL,
	CONSTRAINT freezeritementity_pkey PRIMARY KEY (freezeritemid),
	CONSTRAINT FK_freezeritementity_shelfentity FOREIGN KEY (shelfentity_shelfid) REFERENCES public.shelfentity(shelfid)
);