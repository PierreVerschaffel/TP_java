
INSERT INTO role (designation) VALUES
                                   ("Chef de chantier"),("Ouvrier"),("Utilisateur");

INSERT INTO utilisateur (pseudo, password, role_id, admin) VALUES
                                    ("Paul", "$2a$10$JPMO7rPShGPb/Nwf/JOcM.qwf464i4j9TAf8vDPHn3yKFdJwXpvgi", 1, true),
                                    ("Jacques", "2a$10$OT6TgAfVBuYuxKEsq4sbGO3n0jyG5s0rMVf39CQgmxLbK9YOMeFdC" , 2, false);

INSERT INTO chantier (nom, adresse, utilisateur_id) VALUES
                                                        ("Chantier de la piscine", "3 rue de Metz" ,1),
                                                        ("Chantier de la mairie", "adresse de la mairie" ,2);

INSERT INTO tache (nom, temps) VALUES
                                    ("creuser",2);

INSERT INTO consommable (nom) VALUES
                                 ("Pelle");

INSERT INTO operation (nom, date, chantier_id, utilisateur_id, tache_id) VALUES
                                                        ("Creuser", "1999-02-03" ,1 , 2, 1),
                                                        ("Forer", "2012-11-15" ,2, 1, 1);

