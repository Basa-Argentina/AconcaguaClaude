app.controller('controlarRemitosCtrl', function ($scope, $http, $mdDialog, $element, $timeout) {
    const scope = $scope;

    $http.get('/operaciones/controlar_remitos').success(function (data) {
        const json = data;
        const reporte = new jsPDF('l', 'pt', 'A4');
        let posicionY = 40; // Ajusta según sea necesario
        let count = 0;
        let currentPage = 1;
        const itemsPerPage = 30; // Ajusta según sea necesario

        function addNewPageIfNeeded() {
            if (count > 0 && count % itemsPerPage === 0) {
                reporte.addPage();
                currentPage++;
                posicionY = 40; // Ajusta según sea necesario
            }
        }

        reporte.setFontSize(14);
        reporte.setTextColor(0, 0, 0);
        reporte.setFontType('bold');
        reporte.text("REPORTE CONTROL AUTOMATICO DE REMITOS", 250, 25);
        reporte.line(16, 32, 820, 32);

        json.forEach(detalle => {
            addNewPageIfNeeded();

            if (detalle.mensaje.match("entregados") === null) {
                reporte.setTextColor(255, 0, 0);
            } else {
                reporte.setTextColor(0, 0, 0);
            }
            reporte.setFontSize(8);
            reporte.setFontType('bold');
            reporte.text("Requerimiento: ", 15, posicionY);
            reporte.setFontType('normal');
            reporte.text(String(detalle.requerimiento), 80, posicionY);
            reporte.setFontType('bold');
            reporte.text("Detalle: ", 115, posicionY);
            reporte.setFontType('normal');
            reporte.text(detalle.mensaje, 150, posicionY);

            posicionY += 15; // Ajusta según sea necesario
            count++;
        });

        $timeout(function () {
            $mdDialog.cancel();

            // Guarda el PDF en una variable y obtén la URL del blob
            const pdfBlob = reporte.output('blob');
            const url = URL.createObjectURL(pdfBlob);

            // Crea un enlace (link) y haz clic para abrir el PDF en una nueva ventana/tab
           // const link = document.createElement('a');
          //  link.href = url;
         //   link.target = '_blank';
          //  link.download = 'reporte.pdf';
         //   document.body.appendChild(link);
        //    link.click();
        //    document.body.removeChild(link);
            window.open(url, '_blank');
            // Libera los recursos del blob
            URL.revokeObjectURL(url);
        });
    }).error(function () {
        $mdDialog.cancel();
    });
});