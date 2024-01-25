import React, { useState } from "react";
import "./IndexPage.css";
import LoginButton from "./LoginButton";
import LogoutButton from "./LogoutButton";
import Profile from "./Profile";
import { useAuth0 } from "@auth0/auth0-react";

const IndexPage: React.FC = () => {
  const [isProfileVisible, setIsProfileVisible] = useState(false);
  const { isLoading, error } = useAuth0();

  const toggleProfile = () => {
    setIsProfileVisible((prev) => !prev);
  };
  return (
    <>
      <div>
        {error && <p>Authentication Error</p>}
        {!error && isLoading && <p>Loading...</p>}
        {!error && !isLoading && (
          <>
            <LoginButton />
            <LogoutButton />
            <button type="button" onClick={toggleProfile}>
              Korisnički profil
            </button>
            {isProfileVisible && <Profile />}
          </>
        )}
      </div>
      <div className="buttons">
        <button type="button" id="dataTableBtn">
          <a href="datatable">Datatable.html</a>
        </button>
        <button type="button" id="jsonBtn">
          <a href="ljubimci.json" download>
            Preuzmi json
          </a>
        </button>
        <button type="button" id="csvBtn">
          <a href="ljubimci.csv" download>
            Preuzmi csv
          </a>
        </button>
      </div>

      <div className="container">
        <h3 className="title">Ljubimci</h3>
        <p>
          Ovaj repozitorij sadrži podatke o različitim ljubimcima, uključujući
          informacije o njihovim imenima, vrstama, spolovima, bojama, dobima,
          prehrani, adresama, veterinarima, vlasnicima. Podaci su organizirani u
          strukturi tablice kako bi omogućili jednostavan pristup i pregled.
        </p>

        <h3 className="title">Creative Commons Zero v1.0 Universal (CC0)</h3>
        <p>
          vrsta je licenciranja za autorska prava koja omogućuje autorima da
          svoja djela u potpunosti oslobode od autorskih prava i dopuste
          slobodnu upotrebu, dijeljenje i prilagodbu tih djela od strane drugih
          korisnika. Ovo je jedna od najotvorenijih i najfleksibilnijih licenci
          koja praktički omogućava da djela postanu dijelom "javnog dobra."
        </p>

        <h3 className="title">Naziv autora:</h3>
        <p>Patrik Marinić</p>

        <h3 className="title">Verzija skupa podataka:</h3>
        <p>1.0</p>

        <h3 className="title">Jezik u kojemu se nalaze podaci:</h3>
        <p>Hrvatski</p>

        <h3 className="title">
          Opis atributa koji se nalaze u skupu podataka:
        </h3>
        <div className="opisAtributa">
          <p>
            <b>1. ID:</b> Jedinstveni identifikator za svakog ljubimca u bazi
            podataka. Koristi se za jednoznačno razlikovanje ljubimaca.
          </p>
          <p>
            <b>2. Ime:</b> Ime ljubimca, obično dodijeljeno od strane vlasnika
            ili skrbnika ljubimca.
          </p>
          <p>
            <b>3. Vrsta:</b> Vrsta ljubimca, kao što su pas, mačka, kornjača,
            hrčak, itd.
          </p>
          <p>
            <b>4. Spol:</b> Spol ljubimca, može biti "mužjak" ili "ženka."
          </p>
          <p>
            <b>5. Dob:</b> Dob ljubimca, izražena u godinama.
          </p>
          <p>
            <b>6. Boja:</b> Boja ljubimca, opisuje njegovu vanjsku boju ili
            uzorak.
          </p>
          <p>
            <b>7. Prehrana:</b> Informacije o prehrani ljubimca može biti
            mesožder, biljožder ili svežder.
          </p>
          <p>
            <b>8. Adresa:</b> Adresa na koju je prijavljen ljubimac.
          </p>
          <p>
            <b>9. Veterinar:</b> Ime i prezime veterinara ljubimca.
          </p>
          <p>
            <b>10. Vlasnik (ime, prezime):</b> Ovo je složeni atribut koji
            sadrži podatke o imenu i prezimenu vlasnika ljubimca. Sastoji se od
            dva podatributa:
          </p>
          <p className="indent">
            <b>1. Ime:</b> Ime vlasnika.
          </p>
          <p className="indent">
            <b>2. Prezime:</b> Prezime vlasnika.
          </p>
        </div>
      </div>
    </>
  );
};

export default IndexPage;
