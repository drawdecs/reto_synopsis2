CREATE TABLE "access".client (
	"name" varchar(150) NULL,
	"clientId" int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
	email varchar(150) NULL,
	phone varchar(15) NULL,
	CONSTRAINT client_pk PRIMARY KEY ("clientId")
)