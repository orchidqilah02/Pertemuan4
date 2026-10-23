
package com.example.pertemuan4.model;

public abstract class Person extends BaseEntity {

    private String nama;
    private String email;

    public Person() {
        super();
    }

    public Person(String nama, String email) {
        super();
        setNama(nama);
        setEmail(email);
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        if (nama == null || nama.trim().length() < 3) {
            throw new IllegalArgumentException(
                    "Nama wajib diisi minimal 3 karakter."
            );
        }
        this.nama = nama.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? "" : email.trim();
    }

    public abstract String getRole();
}
