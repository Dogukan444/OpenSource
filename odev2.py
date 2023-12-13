import requests
from PIL import Image
from io import BytesIO


def download_and_open_image():
    # API'den resim linkini al
    response = requests.get("https://randomfox.ca/floof/")

    # İsteğin başarılı olup olmadığını kontrol et
    if response.status_code == 200:
        # JSON formatındaki veriyi çözümle
        data = response.json()

        # Resim linkini al
        image_url = data.get('image', '')

        if image_url:
            # Resmi indir
            image_response = requests.get(image_url)

            # İndirilen veriyi bir PIL Image nesnesine çevir
            image_data = BytesIO(image_response.content)
            image = Image.open(image_data)

            # Resmi ekranda aç
            image.show()
        else:
            print("Hata: Resim linki bulunamadı.")
    else:
        print("Hata: İstek başarısız oldu. HTTP Status Code:", response.status_code)


if __name__ == "__main__":
    download_and_open_image()
