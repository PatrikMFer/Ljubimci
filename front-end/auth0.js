import Auth0 from "auth0-js";

// Funkcija za generiranje nasumičnih vrijednosti
const generateRandomValue = () => {
  return Math.random().toString(36).substring(2);
};

// Konfiguracija Auth0
const auth0Config = {
  domain: "tvoj-auth0-domain",
  clientId: "tvoj-client-id",
  redirectUri: window.location.origin,
  responseType: "token id_token",
  scope: "openid profile",
};

// Kreiranje Auth0 klijenta
const auth0Client = new Auth0.WebAuth(auth0Config);

// Funkcija za pohranu state-a (možeš koristiti sessionStorage, localStorage, itd.)
const storeState = (state) => {
  sessionStorage.setItem("authState", state);
};

// Funkcija za dohvat pohranjenog state-a
const getStoredState = () => {
  return sessionStorage.getItem("authState");
};

// Funkcija za prijavu
const login = () => {
  // Generiranje nasumičnog state-a
  const state = generateRandomValue();

  // Pohrana state-a
  storeState(state);

  // Pokretanje autentikacije s Auth0
  auth0Client.authorize({ state });
};

// Funkcija za odjavu
const logout = () => {
  // Pokretanje odjave s Auth0
  auth0Client.logout({ returnTo: window.location.origin });
};

// Funkcija za obradu odgovora nakon autentikacije
const handleAuthenticationResponse = () => {
  // Dobivanje parametara iz URL-a
  const params = new URLSearchParams(window.location.search);
  const stateFromUrl = params.get("state");

  // Dobivanje pohranjenog state-a
  const storedState = getStoredState();

  // Usporedba state-a iz URL-a i pohranjenog state-a
  if (stateFromUrl === storedState) {
    // Odgovor je pouzdan, nastavi s obradom
    const accessToken = params.get("access_token");
    const idToken = params.get("id_token");

    // Ovdje možeš obraditi token-e ili druge informacije
  } else {
    // Ne podudara se state, mogući pokušaj napada
    console.error("Nevažeći state. Mogući pokušaj napada.");
  }
};

// Dodavanje event listener-a na gumb za prijavu
document.getElementById("loginBtn").addEventListener("click", () => {
  login();
});

// Dodavanje event listener-a na gumb za odjavu
document.getElementById("logoutBtn").addEventListener("click", () => {
  logout();
});

// Obrada odgovora nakon autentikacije
handleAuthenticationResponse();
