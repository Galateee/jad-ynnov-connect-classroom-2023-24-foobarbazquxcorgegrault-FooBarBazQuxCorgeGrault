# REFLEXION

Choix d’implémentation
- `Foo` détient des collections concrètes (`ArrayList`) et expose des getters pour lisibilité et simplicité.
- `Qux` est instancié par défaut dans `Foo` pour satisfaire le test `quxTest()` sans logique additionnelle.
- `addGrault()` crée un `Grault` lié au `Foo` appelant via constructeur (référence immuable) afin de vérifier facilement `grault.getFoo()` dans les tests.
- Relation `Foo` ↔ `Corge` bidirectionnelle: 
  - `Foo#setCorge` met à jour la référence et délient l’ancien `Corge` si présent (en faisant `old.setFoo(null)`).
  - `Corge#setFoo` synchronise côté `Foo` si nécessaire. Des gardes empêchent les boucles.

Difficultés rencontrées
- Boucles potentielles entre `setCorge` et `setFoo`. Résolu avec des checks `if (this.corge == newCorge)` et `if (newCorge != null && newCorge.getCorge() != this)`.
- Exceptions `UnsupportedOperationException` initiales (stubs) dans les squelettes: nécessaire d’implémenter toutes les méthodes invoquées par `FooTest`.
- Assurer que `Qux` n’est jamais null sans devoir ajouter de logique lourde: solution simple avec initialisation directe.

Améliorations possibles
- Rendre les collections retournées par `Foo` non modifiables (ex. `Collections.unmodifiableList`) pour ne pas exposer l’état interne.
- Ajouter des tests supplémentaires pour `setCorge` couvrant: remplacement multiple, passage à `null`, idempotence, et non-régression.
- Ajouter de la JavaDoc sur `Foo#setCorge` et `Corge#setFoo` pour documenter les invariants de la relation bidirectionnelle.
- Introduire un builder pour `Foo` si la construction devenait plus complexe.
- Éventuellement, rendre `Corge` et `Grault` immuables (sauf leur lien maintenu par design) selon les besoins futurs.
