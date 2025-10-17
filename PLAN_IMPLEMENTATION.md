# Mini-plan d’implémentation

Contexte
- Projet Java Maven avec classes Foo/Bar/Baz/Qux/Corge/Grault et tests JUnit 5 (`FooTest`).

Objectif
- Satisfaire `FooTest` en implémentant les comportements attendus, et fournir un code compilable avec CI.

Étapes d’implémentation
1. Modéliser les données et relations
   - `Foo` détient:
     - `Bar` (obligatoire)
     - `List<Baz>` (ajouts via `addBaz`)
     - `Qux` (présent par défaut)
     - `List<Grault>` (créés via `addGrault()`)
     - `Corge` (référence unique, relation bidirectionnelle)
   - `Grault` contient une référence au `Foo` créateur (immuable).
   - `Corge` garde une référence à un `Foo` et synchronise la relation avec `Foo#setCorge`.

2. Implémentation des méthodes dans `Foo`
   - Getters basiques: `getBar`, `getBazs`, `getQux`, `getGraults`, `getCorge`.
   - Mutateurs: `addBaz`, `addGrault` (instancie `new Grault(this)`), `setCorge` (gère le déliaison/liaison proprement).

3. Implémentation de `Grault`
   - `private final Foo foo;` + constructeur `Grault(Foo)` + `getFoo()`.

4. Implémentation de `Corge`
   - Constructeur appelant `foo.setCorge(this)` si nécessaire.
   - `setFoo(Foo)` met à jour la référence et synchronise côté `Foo`.

5. Validation
   - `mvn test` -> 5 tests, 0 échecs.

6. CI GitHub
   - Ajouter un workflow `maven.yml` pour compiler et exécuter les tests sur GitHub Actions.

Notes
- Des garde-fous évitent les cycles d’appels entre `Foo#setCorge` et `Corge#setFoo`.
- Les setters superflus sont évités pour limiter la surface d’API.
