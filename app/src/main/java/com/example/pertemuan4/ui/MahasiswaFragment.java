package com.example.pertemuan4.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pertemuan4.R;
import com.example.pertemuan4.model.Mahasiswa;
import com.example.pertemuan4.model.Person;
import com.example.pertemuan4.viewmodel.MahasiswaViewModel;

public class MahasiswaFragment extends Fragment {

    private EditText edtNama, edtEmail, edtNim, edtJurusan, edtIpk;
    private TextView txtPesan;
    private Button btnSimpan;
    private MahasiswaViewModel viewModel;
    private AkademikAdapter adapter;
    private Mahasiswa mahasiswaEdit;

    public MahasiswaFragment() {
        // Constructor kosong diperlukan oleh FragmentManager.
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(
                R.layout.fragment_mahasiswa, container, false);
    }

    @Override
    public void onViewCreated(
            @NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtNama = view.findViewById(R.id.edtNama);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtNim = view.findViewById(R.id.edtNim);
        edtJurusan = view.findViewById(R.id.edtJurusan);
        edtIpk = view.findViewById(R.id.edtIpk);
        txtPesan = view.findViewById(R.id.txtPesan);
        btnSimpan = view.findViewById(R.id.btnSimpan);

        RecyclerView recyclerView =
                view.findViewById(R.id.rvMahasiswa);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(requireContext()));

        adapter = new AkademikAdapter(
                new AkademikAdapter.Listener() {
                    @Override
                    public void onEdit(Person person) {
                        if (person instanceof Mahasiswa) {
                            mahasiswaEdit = (Mahasiswa) person;
                            edtNama.setText(mahasiswaEdit.getNama());
                            edtEmail.setText(mahasiswaEdit.getEmail());
                            edtNim.setText(mahasiswaEdit.getNim());
                            edtJurusan.setText(mahasiswaEdit.getJurusan());
                            edtIpk.setText(
                                    String.valueOf(mahasiswaEdit.getIpk()));
                            btnSimpan.setText("Perbarui Mahasiswa");
                        }
                    }

                    @Override
                    public void onDelete(Person person) {
                        if (person instanceof Mahasiswa) {
                            viewModel.hapus((Mahasiswa) person);
                            if (mahasiswaEdit != null &&
                                    mahasiswaEdit.getId() == person.getId()) {
                                kosongkanForm();
                            }
                        }
                    }
                });

        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this)
                .get(MahasiswaViewModel.class);

        viewModel.getSemuaMahasiswa().observe(
                getViewLifecycleOwner(), adapter::setData);

        viewModel.getPesanError().observe(
                getViewLifecycleOwner(), pesan -> {
                    if (pesan != null && !pesan.isEmpty()) {
                        txtPesan.setText(pesan);
                        Toast.makeText(requireContext(),
                                pesan, Toast.LENGTH_SHORT).show();

                        if (pesan.startsWith("Data mahasiswa berhasil")
                                || pesan.startsWith("Data berhasil diperbarui.")) {
                            kosongkanForm();
                        }
                    }
                });

        btnSimpan.setOnClickListener(v -> simpanData());
    }

    private void simpanData() {
        String nama = edtNama.getText().toString();
        String email = edtEmail.getText().toString();
        String nim = edtNim.getText().toString();
        String jurusan = edtJurusan.getText().toString();
        String ipk = edtIpk.getText().toString();

        if (mahasiswaEdit == null) {
            viewModel.simpan(nama, email, nim, jurusan, ipk);
        } else {
            viewModel.edit(mahasiswaEdit, nama, email,
                    nim, jurusan, ipk);
        }
    }

    private void kosongkanForm() {
        edtNama.setText("");
        edtEmail.setText("");
        edtNim.setText("");
        edtJurusan.setText("");
        edtIpk.setText("");
        mahasiswaEdit = null;
        btnSimpan.setText("Simpan Mahasiswa");
    }
}
