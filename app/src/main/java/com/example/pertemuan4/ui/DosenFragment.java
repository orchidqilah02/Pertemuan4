
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
import com.example.pertemuan4.model.Dosen;
import com.example.pertemuan4.model.Person;
import com.example.pertemuan4.viewmodel.DosenViewModel;

public class DosenFragment extends Fragment {

    private EditText edtNama, edtEmail, edtNidn, edtMataKuliah;
    private TextView txtPesan;
    private Button btnSimpan;
    private DosenViewModel viewModel;
    private Dosen dosenEdit;

    public DosenFragment() {
        // Constructor kosong.
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(
                R.layout.fragment_dosen, container, false);
    }

    @Override
    public void onViewCreated(
            @NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtNama = view.findViewById(R.id.edtNamaDosen);
        edtEmail = view.findViewById(R.id.edtEmailDosen);
        edtNidn = view.findViewById(R.id.edtNidn);
        edtMataKuliah = view.findViewById(R.id.edtMataKuliah);
        txtPesan = view.findViewById(R.id.txtPesanDosen);
        btnSimpan = view.findViewById(R.id.btnSimpanDosen);

        RecyclerView recyclerView = view.findViewById(R.id.rvDosen);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(requireContext()));

        AkademikAdapter adapter = new AkademikAdapter(
                new AkademikAdapter.Listener() {
                    @Override
                    public void onEdit(Person person) {
                        if (person instanceof Dosen) {
                            dosenEdit = (Dosen) person;
                            edtNama.setText(dosenEdit.getNama());
                            edtEmail.setText(dosenEdit.getEmail());
                            edtNidn.setText(dosenEdit.getNidn());
                            edtMataKuliah.setText(
                                    dosenEdit.getMataKuliahDiampu());
                            btnSimpan.setText("Perbarui Dosen");
                        }
                    }

                    @Override
                    public void onDelete(Person person) {
                        if (person instanceof Dosen) {
                            viewModel.hapus((Dosen) person);

                            if (dosenEdit != null &&
                                    dosenEdit.getId() == person.getId()) {
                                kosongkanForm();
                            }
                        }
                    }
                });

        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this)
                .get(DosenViewModel.class);

        viewModel.getSemuaDosen().observe(
                getViewLifecycleOwner(), adapter::setData);

        viewModel.getPesan().observe(
                getViewLifecycleOwner(), pesan -> {
                    if (pesan != null && !pesan.isEmpty()) {
                        txtPesan.setText(pesan);
                        Toast.makeText(requireContext(),
                                pesan, Toast.LENGTH_SHORT).show();

                        if (pesan.startsWith("Data dosen berhasil")) {
                            kosongkanForm();
                        }
                    }
                });

        btnSimpan.setOnClickListener(v -> simpanData());
    }

    private void simpanData() {
        String nama = edtNama.getText().toString();
        String email = edtEmail.getText().toString();
        String nidn = edtNidn.getText().toString();
        String mataKuliah = edtMataKuliah.getText().toString();

        if (dosenEdit == null) {
            viewModel.simpan(nama, email, nidn, mataKuliah);
        } else {
            viewModel.edit(dosenEdit, nama, email,
                    nidn, mataKuliah);
        }
    }

    private void kosongkanForm() {
        edtNama.setText("");
        edtEmail.setText("");
        edtNidn.setText("");
        edtMataKuliah.setText("");
        dosenEdit = null;
        btnSimpan.setText("Simpan Dosen");
    }
}
