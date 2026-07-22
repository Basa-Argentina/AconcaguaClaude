app.controller('controlarRemitosCtrl', function ($scope, $http, $mdDialog, $element, $timeout, $interval) {
    const scope = $scope;

    $scope.progresoProcesado = 0;
    $scope.progresoTotal     = 0;
    $scope.progresoPct       = 0;

    // Polling de progreso cada 2 segundos mientras procesa
    var pollingProgreso = $interval(function () {
        $http.get('/operaciones/controlar_remitos/progreso').success(function (p) {
            $scope.progresoProcesado = p.procesados;
            $scope.progresoTotal     = p.total;
            $scope.progresoPct       = p.total > 0 ? Math.round((p.procesados / p.total) * 100) : 0;
        });
    }, 2000);

    $http.get('/operaciones/controlar_remitos').success(function (data) {
        $interval.cancel(pollingProgreso);
        $scope.progresoPct = 100;
        const json = data;
        const reporte = new jsPDF('l', 'pt', 'A4');
        // A4 landscape: 841 x 595 pts. Margen inferior en 575.
        const pageHeight  = 575;
        const lineHeight  = 12;
        const xDetalle    = 150;
        const maxAncho    = 841 - xDetalle - 15; // ancho disponible para el detalle
        let posicionY = 40;

        function encabezado() {
            reporte.setFontSize(14);
            reporte.setTextColor(0, 0, 0);
            reporte.setFontType('bold');
            reporte.text("REPORTE CONTROL AUTOMATICO DE REMITOS", 250, 25);
            reporte.line(16, 32, 820, 32);
        }

        encabezado();

        json.forEach(detalle => {
            reporte.setFontSize(8);

            // Cortar el mensaje al ancho disponible
            var lineas = reporte.splitTextToSize(detalle.mensaje, maxAncho);
            var altoEntrada = lineas.length * lineHeight;

            // Nueva página si no entra
            if (posicionY + altoEntrada > pageHeight) {
                reporte.addPage();
                posicionY = 40;
                encabezado();
            }

            if (detalle.mensaje.match("entregados") === null) {
                reporte.setTextColor(255, 0, 0);
            } else {
                reporte.setTextColor(0, 0, 0);
            }

            reporte.setFontType('bold');
            reporte.text("Requerimiento: ", 15, posicionY);
            reporte.setFontType('normal');
            reporte.text(String(detalle.requerimiento), 80, posicionY);
            reporte.setFontType('bold');
            reporte.text("Detalle: ", 115, posicionY);
            reporte.setFontType('normal');
            reporte.text(lineas, xDetalle, posicionY);

            posicionY += altoEntrada + 3;
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
        $interval.cancel(pollingProgreso);
        $mdDialog.cancel();
    });
});