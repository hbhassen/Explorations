import json
from pathlib import Path


def build_people():
    first_names = [
        "Amir", "Aisha", "John", "Maria", "Wei", "Ling", "Diego", "Lucia", "Omar", "Sara",
        "Kenji", "Naoko", "Sergei", "Anya", "David", "Leah", "Yusuf", "Mina", "Kwame", "Ama",
        "Carlos", "Fatima", "Noah", "Emma", "Hassan", "Zara", "Ivan", "Elena", "Jamal", "Nora",
    ]
    last_names = [
        "Haddad", "Chen", "Smith", "Garcia", "Khan", "Kim", "Petrov", "Singh", "Okafor", "Diaz",
        "Nguyen", "Brown", "Hernandez", "Ali", "Ito", "Dubois", "Ivanov", "Mensah", "Rahman", "Silva",
        "Kumar", "Tanaka", "Bello", "Morales", "Costa", "Lopez", "Yamamoto", "Hassan", "Santos", "Omar",
    ]

    people = []
    for i in range(100):
        fn = first_names[i % len(first_names)]
        ln = last_names[(i * 2 + 3) % len(last_names)]
        year = 1980 + (i % 30)
        month = (i % 12) + 1
        day = (i % 28) + 1
        people.append({
            "nom": ln,
            "prenom": fn,
            "adresse": f"{i + 1} Example Street, City{(i % 15) + 1}",
            "dateNaissance": f"{year:04d}-{month:02d}-{day:02d}",
            "email": f"{fn.lower()}.{ln.lower()}@example.com"
        })
    return people


def main():
    target = Path(__file__).resolve().parent.parent / "src" / "main" / "resources" / "personnes.json"
    people = build_people()
    target.write_text(json.dumps(people, ensure_ascii=True, indent=2), encoding="utf-8")


if __name__ == "__main__":
    main()
