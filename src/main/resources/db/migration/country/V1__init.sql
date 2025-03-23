-- Ensure UUID generation extension is available
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create the table if it doesn't exist
CREATE TABLE IF NOT EXISTS "country"
(
    id           UUID UNIQUE         NOT NULL DEFAULT uuid_generate_v1() PRIMARY KEY,
    country_name VARCHAR(255) UNIQUE NOT NULL,
    country_code VARCHAR(3) UNIQUE   NOT NULL
);

-- Ensure the owner of the table is set to postgres
ALTER TABLE "country"
    OWNER TO postgres;

-- Clear existing data in the table to prevent duplicates
DELETE
FROM "country";

-- Insert countries into the table
INSERT INTO "country" (country_name, country_code)
VALUES ('Fiji', 'FJ'),
       ('Tanzania', 'TZ'),
       ('Western Sahara', 'EH'),
       ('Canada', 'CA'),
       ('United States', 'US'),
       ('Kazakhstan', 'KZ'),
       ('Uzbekistan', 'UZ'),
       ('Papua New Guinea', 'PG'),
       ('Indonesia', 'ID'),
       ('Argentina', 'AR'),
       ('Chile', 'CL'),
       ('Democratic Republic of the Congo', 'CD'),
       ('Somalia', 'SO'),
       ('Kenya', 'KE'),
       ('Sudan', 'SD'),
       ('Chad', 'TD'),
       ('Haiti', 'HT'),
       ('Dominican Republic', 'DO'),
       ('Russia', 'RU'),
       ('Bahamas', 'BS'),
       ('Falkland Islands', 'FK'),
       ('Norway', 'NO'),
       ('Greenland', 'GL'),
       ('Timor-Leste', 'TL'),
       ('South Africa', 'ZA'),
       ('Lesotho', 'LS'),
       ('Mexico', 'MX'),
       ('Uruguay', 'UY'),
       ('Brazil', 'BR'),
       ('Bolivia', 'BO'),
       ('Peru', 'PE'),
       ('Colombia', 'CO'),
       ('Panama', 'PA'),
       ('Costa Rica', 'CR'),
       ('Nicaragua', 'NI'),
       ('Honduras', 'HN'),
       ('El Salvador', 'SV'),
       ('Guatemala', 'GT'),
       ('Belize', 'BZ'),
       ('Venezuela', 'VE'),
       ('Guyana', 'GY'),
       ('Suriname', 'SR'),
       ('France', 'FR'),
       ('Ecuador', 'EC'),
       ('Puerto Rico', 'PR'),
       ('Jamaica', 'JM'),
       ('Cuba', 'CU'),
       ('Zimbabwe', 'ZW'),
       ('Botswana', 'BW'),
       ('Namibia', 'NA'),
       ('Senegal', 'SN'),
       ('Mali', 'ML'),
       ('Mauritania', 'MR'),
       ('Benin', 'BJ'),
       ('Niger', 'NE'),
       ('Nigeria', 'NG'),
       ('Cameroon', 'CM'),
       ('Togo', 'TG'),
       ('Ghana', 'GH'),
       ('Guinea', 'GN'),
       ('Guinea-Bissau', 'GW'),
       ('Liberia', 'LR'),
       ('Sierra Leone', 'SL'),
       ('Burkina Faso', 'BF'),
       ('Central African Republic', 'CF'),
       ('Republic of the Congo', 'CG'),
       ('Gabon', 'GA'),
       ('Equatorial Guinea', 'GQ'),
       ('Zambia', 'ZM'),
       ('Malawi', 'MW'),
       ('Mozambique', 'MZ'),
       ('Eswatini', 'SZ'),
       ('Angola', 'AO'),
       ('Burundi', 'BI'),
       ('Israel', 'IL'),
       ('Lebanon', 'LB'),
       ('Madagascar', 'MG'),
       ('Palestine', 'PS'),
       ('The Gambia', 'GM'),
       ('Tunisia', 'TN'),
       ('Algeria', 'DZ'),
       ('Jordan', 'JO'),
       ('United Arab Emirates', 'AE'),
       ('Qatar', 'QA'),
       ('Kuwait', 'KW'),
       ('Iraq', 'IQ'),
       ('Oman', 'OM'),
       ('Vanuatu', 'VU'),
       ('Cambodia', 'KH'),
       ('Thailand', 'TH'),
       ('Lao PDR', 'LA'),
       ('Myanmar', 'MM'),
       ('Vietnam', 'VN'),
       ('Dem. Rep. Korea', 'KP'),
       ('Republic of Korea', 'KR'),
       ('Mongolia', 'MN'),
       ('India', 'IN'),
       ('Bangladesh', 'BD'),
       ('Bhutan', 'BT'),
       ('Nepal', 'NP'),
       ('Pakistan', 'PK'),
       ('Afghanistan', 'AF'),
       ('Tajikistan', 'TJ'),
       ('Kyrgyzstan', 'KG'),
       ('Turkmenistan', 'TM'),
       ('Iran', 'IR'),
       ('Syria', 'SY'),
       ('Armenia', 'AM'),
       ('Sweden', 'SE'),
       ('Belarus', 'BY'),
       ('Ukraine', 'UA'),
       ('Poland', 'PL'),
       ('Austria', 'AT'),
       ('Hungary', 'HU'),
       ('Moldova', 'MD'),
       ('Romania', 'RO'),
       ('Lithuania', 'LT'),
       ('Latvia', 'LV'),
       ('Estonia', 'EE'),
       ('Germany', 'DE'),
       ('Bulgaria', 'BG'),
       ('Greece', 'GR'),
       ('Turkey', 'TR'),
       ('Albania', 'AL'),
       ('Croatia', 'HR'),
       ('Switzerland', 'CH'),
       ('Luxembourg', 'LU'),
       ('Belgium', 'BE'),
       ('Netherlands', 'NL'),
       ('Portugal', 'PT'),
       ('Spain', 'ES'),
       ('Ireland', 'IE'),
       ('New Caledonia', 'NC'),
       ('Solomon Islands', 'SB'),
       ('New Zealand', 'NZ'),
       ('Australia', 'AU'),
       ('Sri Lanka', 'LK'),
       ('China', 'CN'),
       ('Taiwan', 'TW'),
       ('Italy', 'IT'),
       ('Denmark', 'DK'),
       ('United Kingdom', 'GB'),
       ('Iceland', 'IS'),
       ('Azerbaijan', 'AZ'),
       ('Georgia', 'GE'),
       ('Philippines', 'PH'),
       ('Malaysia', 'MY'),
       ('Brunei Darussalam', 'BN'),
       ('Slovenia', 'SI'),
       ('Finland', 'FI'),
       ('Slovakia', 'SK'),
       ('Czech Republic', 'CZ'),
       ('Eritrea', 'ER'),
       ('Japan', 'JP'),
       ('Paraguay', 'PY'),
       ('Yemen', 'YE'),
       ('Saudi Arabia', 'SA'),
       ('Northern Cyprus', 'CYP'),
       ('Cyprus', 'CY'),
       ('Morocco', 'MA'),
       ('Egypt', 'EG'),
       ('Libya', 'LY'),
       ('Ethiopia', 'ET'),
       ('Djibouti', 'DJ'),
       ('Somaliland', 'SOM'),
       ('Uganda', 'UG'),
       ('Rwanda', 'RW'),
       ('Bosnia and Herzegovina', 'BA'),
       ('Macedonia', 'MK'),
       ('Serbia', 'RS'),
       ('Montenegro', 'ME'),
       ('Kosovo', 'XK'),
       ('Trinidad and Tobago', 'TT'),
       ('South Sudan', 'SS');