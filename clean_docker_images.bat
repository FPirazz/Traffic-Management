# Define the image names
$images = "tcm_frontend:latest", "fpirazzoli/sap_spe_db:latest", "src/main/userContext:latest"

# Loop through the images
foreach ($image in $images) {
    # Pull the latest image
    docker pull $image

    # Get the image ID of the local image
    $local_image_id = docker images -q $image

    # Get the image ID of the remote image
    $remote_image_id = docker inspect --format='{{.Id}}' $image

    # Compare the image IDs
    if ($local_image_id -ne $remote_image_id) {
        # If the image IDs are different, remove the local image
        docker rmi $local_image_id
    }
}