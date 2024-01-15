import * as auth0 from "./auth0.js";

document.getElementById("loginBtn").addEventListener("click", () => {
  auth0.login();
});

document.getElementById("logoutBtn").addEventListener("click", () => {
  auth0.logout();
});

// Dodaj event listener koji će obraditi autentikaciju i ispisati podatke o korisniku
auth0.obradiAutentikaciju();
