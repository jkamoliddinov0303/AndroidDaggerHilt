package uz.jkamoliddinov0303.androiddaggerhilt.ui.main

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.custom_alert_dialog.view.*
import kotlinx.android.synthetic.main.fragment_main.*
import uz.jkamoliddinov0303.androiddaggerhilt.R
import uz.jkamoliddinov0303.androiddaggerhilt.adapter.RvAdapter
import uz.jkamoliddinov0303.androiddaggerhilt.db.User

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var rvAdapter: RvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        rvAdapter = RvAdapter(listOf())

        viewModel.getAllUsers.observe(viewLifecycleOwner, Observer {
            rvAdapter.setData(it)
            for (i in it.indices)
                Log.d("TESTUSER", "${it[i].name}: ${it[i].country}:${it[i].id}")
        })
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        recyclerview.adapter = rvAdapter
        btn_save.setOnClickListener {
            if (et_username.text.toString().isNotEmpty() && et_username.text.toString()
                    .isNotEmpty()
            ) {
                insertData(User(et_username.text.toString(), et_country.text.toString()))
            }
        }
        rvAdapter.setOnUserClickedListener(object : RvAdapter.OnUserClickListener {
            override fun onUserClicked(user: User) {
                showAlertDialog(user)
            }
        })
    }

    private fun insertData(user: User) {
        viewModel.insert(user)
        et_username.setText("")
        et_country.setText("")
    }

    private fun updateUser(user: User) {
        viewModel.update(user)
        rvAdapter.notifyDataSetChanged()
    }

    private fun deleteUser(user: User) {
        viewModel.delete(user)
    }

    @SuppressLint("InflateParams")
    private fun showAlertDialog(user: User) {
        val builder = AlertDialog.Builder(requireContext())
        val view = layoutInflater.inflate(R.layout.custom_alert_dialog, null, false)

        val alertD = builder
            .setView(view)
            .create()
        alertD.show()
        view.et_username1.setText(user.name)
        view.et_country1.setText(user.country)

        view.btn_update.setOnClickListener {

            if (view.et_username1.text.toString().isNotEmpty() && view.et_country1.text.toString()
                    .isNotEmpty()
            ) {
                updateUser(
                    User(
                        view.et_username1.text.toString(),
                        view.et_country1.text.toString()
                    )
                )
                Toast.makeText(
                    requireContext(), "User Updated${
                        view.et_username1.text.toString()
                                + " " + view.et_country1.text.toString()
                    }", Toast.LENGTH_SHORT
                ).show()
            }
            alertD.dismiss()
        }
        view.btn_delete.setOnClickListener {
            deleteUser(user)
            alertD.dismiss()
        }
        view.btn_cancel.setOnClickListener {
            alertD.dismiss()
        }
    }
}