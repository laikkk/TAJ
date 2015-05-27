Given a Psikus
When set liczba to 9
Then hultajchochla should throw NieduanyPsikusException

When set liczba to -9
Then hultajchochla should throw NieduanyPsikusException

When set liczba to 10
Then hultajchochla should return 1

When set liczba to -83
Then hultajchochla should return -38

When set liczba to 345
Then hultajchochla should return either 435 354 543



When set liczba to 125
Then nieksztaltek should return 125

When set liczba to 347
Then nieksztaltek return either 847 or 341

When set liczba to 333
Then nieksztaltek return either 833 or 383 or 338

When set liczba to 376
Then nieksztaltek return either 876 or 316 or 379


When set liczba to -5
Then cyfrokrad should return NULL

When set liczba to 843
Then cyfrokrad should return either 84 83 43

When set liczba to 4070
Then cyfrokrad should return either 407 400 470

When set liczba to 1000
Then cyfrokrad should return 100