# Java-dilinde-ozgecmis-program-
Özellikler

PDF formatında özgeçmiş oluşturma.
Özgeçmişe profil fotoğrafı ekleme.
Örnek olarak 3 iş deneyimi içerir.
Kişisel bilgiler ve yetenekler maddeler halinde yazılır.
PDF dosyası proje çalıştırıldığında otomatik olarak oluşturulur.
Basit bir alt çizgi ve imza alanı ile profesyonel bir görünüm sağlar.

Kullanılan Teknolojiler ve Kütüphaneler

Java SE: Programın temelini oluşturan dil.
Apache PDFBox: PDF dosyaları oluşturmak, düzenlemek ve içerik eklemek için kullanılan açık kaynak kütüphane.
AWT Color: PDF’de yazı ve çizgi renklerini belirlemek için kullanılır.
InputStream ve ByteArrayOutputStream: Profil fotoğrafını güvenli bir şekilde PDF’e eklemek için kullanılır.

Programın Çalışma Mantığı

PDF Dosyası Oluşturma
Program yeni bir PDF belgesi başlatır ve bir sayfa ekler.

Profil Fotoğrafı Ekleme
Fotoğraf, projenin resources klasöründen okunur ve PDF’e eklenir.
Program, fotoğrafın varlığını kontrol eder ve yoksa hata mesajı verir.

Başlık ve Bölümler
PDF’in üst kısmına büyük fontla “OZGECMIS” başlığı eklenir.
Ardından kişisel bilgiler, iş deneyimleri ve yetenekler bölümleri madde madde yazılır.

Görsel Düzenleme
Başlık altına ve sayfanın altına çizgiler eklenir.
Yazı tipleri ve boyutları PDFBox kütüphanesindeki standart fontlarla ayarlanır.

Dosya Kaydetme
PDF, proje çalıştırıldığında ozgecmis.pdf olarak kaydedilir.
Konsol ekranında işlem başarıyla tamamlandığında bilgilendirme mesajı gösterilir.

Nasıl Kullanılır

Proje IntelliJ IDEA veya herhangi bir Java IDE ile açılır.
Profil fotoğrafı src/main/resources klasörüne eklenir (örnek dosya: nazli_gursoy.png).
Program çalıştırılır (ResumeGenerator sınıfı).
Konsol ekranında aşağıdaki gibi mesajlar görünür:
Resmin başarıyla yüklendiği bilgisi
PDF’in başarıyla oluşturulduğu bilgisi
Oluşan PDF dosyası proje kök dizininde (ozgecmis.pdf) bulunur.

Proje Avantajları

Kendi bilgilerin yerine hayali bilgiler girilebilir, böylece test amaçlı veya örnek projelerde kullanılabilir.

Profesyonel ve görsel olarak düzenlenmiş PDF çıktısı sağlar.

Arayüz gerektirmediği için hızlı ve hafif bir çözüm sunar.
