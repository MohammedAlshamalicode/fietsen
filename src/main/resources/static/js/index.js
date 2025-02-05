"use strict";
import {setText, toon, byId} from "./util.js";
const response = await fetch("docenten/aantal");
if (response.ok) {
    const body = await response.text();
    setText("aantal", body);
} else {
    toon("storing");
}


const response1 = await fetch("docenten");
if (response1.ok) {
    const docenten = await response1.json();
    const docentenBody = byId("docentenBody");
    for (const docent of docenten) {
        const tr = docentenBody.insertRow();
        tr.insertCell().textContent = docent.id;
        tr.insertCell().textContent = docent.voornaam;
        tr.insertCell().textContent = docent.familienaam;
        tr.insertCell().textContent = docent.wedde;
        tr.insertCell().textContent = docent.emailAdres;
        tr.insertCell().textContent = docent.geslacht;
    }
} else {
    toon("storing");
}