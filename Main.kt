package com.example.food_order

import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    val menuList = List(
        Menu("Food item 1", "Deskripsi item 1", 85),
        Menu("Food item 2", "Deskripsi item 2", 120),
        Menu("Food item 3", "Deskripsi item 3", 70)
    )
    val orderList = List<Order>()

    while (true) {
        println("\n=== ORDER SYSTEM ===")
        println("1. Make order")
        println("2. View Orders")
        println("3. View Menu")
        println("4. Add Menu")
        println("5. Edit Menu")
        println("6. Delete Menu")
        println("7. Exit")
        print("Pilih menu (1-7): ")

        val choice = scanner.nextLine().intNull()
        if (choice == null) {
            println("ERROR: Input harus angka dari 1 sampai 7")
            continue
        }

        when (choice) {
            1 -> {
                if (menuList.isEmpty()) {
                    println("error: Menunya masih kosong")
                    continue
                }
                print("Masukkan nama customer: ")
                val custName = scanner.nextLine()
                if (custName.isBlank()) {
                    println("error: Nama customer tidak boleh kosong")
                    continue
                }

                println("-- Pilih Nomor Menu yang ada --")
                menuList.forEachIndexed { i, m -> println("\${i + 1}. \({m.name} [\$\){m.price}]") }

                print("Pilih nomor menu: ")
                val nomorMenu = scanner.nextLine().intNull()
                print("Masukkan jumlah porsi: ")
                val porsi = scanner.nextLine().intNull()

                if (nomorMenu != null && nomorMenu in 1..menuList.size && porsi != null && porsi > 0) {
                    val makanan = menuList[nomorMenu - 1]
                    val total = makanan.price * porsi
                    val ringkasan = "1. \${makanan.name} x\(porsi\n\$\){total}"

                    orderList.add(Order(custName, ringkasan, total))
                    println("Pesanan atas nama \$custName berhasil")
                } else {
                    println("error: Nomor menu tidak ada atau jumlah porsi salah")
                }
            }

            2 -> {
                if (orderList.isEmpty()) {
                    println("Belum ada yang beli.")
                } else {
                    orderList.forEach {
                        println("\n\${it.customerName}'s ORDER")
                        println(it.itemsSummary)
                        println("Total")
                        println("\$\${it.totalPrice}")
                    }
                }
            }

            3 -> {
                if (menuList.isEmpty()) {
                    println("Daftar menu kosong.")
                } else {
                    println("--- Daftar Menu ---")
                    menuList.forEachIndexed { index, item ->
                        println("index + 1. {item.name} (item.description) - \${item.price}")
                    }
                }
            }

            4 -> {
                print("Nama Menu Baru: ")
                val nama = scanner.nextLine()
                print("Deskripsi Menu: ")
                val deskripsi = scanner.nextLine()
                print("Harga Menu (\$): ")
                val harga = scanner.nextLine().intNull()

                if (nama.isNotBlank() && deskripsi.isNotBlank() && harga != null && harga > 0) {
                    menuList.add(Menu(nama, deskripsi, harga))
                    println("Menu '\$nama' berhasil ditambahkan ke menu")
                } else {
                    println("error: Gagal menambah menu, Pastikan data diisi dan harga dalam bentuk angka.")
                }
            }

            5 -> {
                if (menuList.isEmpty()) {
                    println("Tidak ada menu untuk diedit.")
                    continue
                }
                print("Masukkan nomor menu yang mau diedit: ")
                val nomorMenu = scanner.nextLine().intNull()

                if (nomorMenu != null && nomorMenu in 1..menuList.size) {
                    val target = menuList[nomorMenu - 1]
                    print("Nama Baru: ")
                    val namaBaru = scanner.nextLine()
                    print("Harga Baru (\$): ")
                    val hargaBaru = scanner.nextLine().intNull()

                    if (namaBaru.isNotBlank() && hargaBaru != null && hargaBaru > 0) {
                        target.name = namaBaru
                        target.price = hargaBaru
                        println("Menu nomor \$nomorMenu berhasil diperbarui!")
                    } else {
                        println("error: Data baru tidak valid!")
                    }
                } else {
                    println("error: Nomor menu tidak ditemukan!")
                }
            }

            6 -> {
                if (menuList.isEmpty()) {
                    println("Daftar menu sudah kosong.")
                    continue
                }
                print("Masukkan nomor menu yang mau dihapus: ")
                val nomorMenu = scanner.nextLine().intNull()

                if (nomorMenu != null && nomorMenu in 1..menuList.size) {
                    val terhapus = menuList.removeAt(nomorMenu - 1)
                    println("Menu '\${terhapus.name}' berhasil dihapus.")
                } else {
                    println("error: Nomor menu salah!")
                }
            }

            7 -> {
                println("Terima kasih")
                break
            }

            else -> println("Pilihan tidak ada, Pilih angka 1 sampai 7.")
        }
    }
}
