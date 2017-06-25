var paginas = [];
var linhas = [];
var linhasCompactadas = [];
var titulos = [];
var volumes = [];
var uvas = [];
var classificacaos = [];
var teorAs = [];

var titulosC = [];
var volumesC = [];
var uvasC = [];
var classificacaosC = [];
var teorAsC = [];

var resultadosAnd = [];
var resultadosOr = [];


$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "saida.txt",
        dataType: "text",
        success: function (data) { processData(data, linhas); }
    });

    $.ajax({
        type: "GET",
        url: "compactado.txt",
        dataType: "text",
        success: function (data2) { processData(data2, linhasCompactadas); }
    });

    $.ajax({
        type: "GET",
        url: "WINE_RI.csv",
        dataType: "text",
        success: function (data3) { lerCSV(data3); }
    });

});

function processData(allText, saida) {
    var allTextLines = allText.split(/\r\n|\n/);
    var lines = [];

    for (var i = 1; i < allTextLines.length; i++) {
        var data = allTextLines[i].split(';');
        saida.push(allTextLines[i].toLocaleLowerCase());

    }
    // alert(lines);
    separar();
}

function lerCSV(entrada) {
    var allTextLines = entrada.split(/\r\n|\n/);
    var lines = [];

    for (var i = 1; i < allTextLines.length; i++) {
        var data = allTextLines[i].split(';');
        paginas.push(allTextLines[i]);

    }
}

console.log(linhas);
console.log(linhasCompactadas);
console.log(paginas);


window.onload = function () {
    document.getElementById("buscar").onclick = function () {
        resultadosOr = [];
        if (document.getElementById("base").value === "1") {
            var aux = document.getElementById("titulo").value;
            var titulo = aux.toLocaleLowerCase();

            buscar(titulo, titulos, resultadosOr);

            var aux = document.getElementById("uva").value;
            var uva = aux.toLocaleLowerCase();

            buscar(uva, uvas, resultadosOr);

            aux = document.getElementById("classificacao").value;
            var classificacao = aux.toLocaleLowerCase();

            buscar(classificacao, classificacaos, resultadosOr);

            aux = document.getElementById("volume").value;
            var volume = aux.toLocaleLowerCase();

            buscar(volume, volumes, resultadosOr);

            aux = document.getElementById("teorA").value;
            var teorA = aux.toLocaleLowerCase();

            buscar(teorA, teorAs, resultadosOr);
            gerarTabela(resultadosOr, "1");

        } else {
            var aux = document.getElementById("titulo").value;
            var titulo = aux.toLocaleLowerCase();

            buscarC(titulo, titulosC, resultadosOr);

            var aux = document.getElementById("uva").value;
            var uva = aux.toLocaleLowerCase();

            buscarC(uva, uvasC, resultadosOr);

            aux = document.getElementById("classificacao").value;
            var classificacao = aux.toLocaleLowerCase();

            buscarC(classificacao, classificacaosC, resultadosOr);

            aux = document.getElementById("volume").value;
            var volume = aux.toLocaleLowerCase();

            buscarC(volume, volumesC, resultadosOr);

            aux = document.getElementById("teorA").value;
            var teorA = aux.toLocaleLowerCase();

            buscarC(teorA, teorAsC, resultadosOr);
            gerarTabela(resultadosOr, "2");
        }


        console.log(resultadosOr);
    };

    document.getElementById("reset").onclick = function () {
        document.getElementById("teorA").value = ""; 
        document.getElementById("volume").value = "";
        document.getElementById("classificacao").value = "";
        document.getElementById("uva").value = "";
        document.getElementById("titulo").value = "";        
    };

};



function gerarTabela(resultados, base) {
    console.log("gerar tabela");
    var table = document.getElementById("tabelinha");

    for (var index = 0; index < resultados.length; index++) {
        if (base === "1") {
            escreverLinha(table, resultados[index], index);
        } else {
            escreverLinha(table, resultados[index], index);
        }
    }
}

function escreverLinha(tabela, resultado, linhaCount) {
    var row = tabela.insertRow(linhaCount + 1);
    var pag = [];
    for (var index = 0; index < paginas.length; index++) {
        var temp = paginas[index].split(";");
        if (temp[0] === resultado) {
            pag = temp;
        }
    }
    for (var index = 1; index < pag.length; index++) {
        var cell = row.insertCell(index-1);
        cell.innerHTML = pag[index];
        if (index === 6) {
            console.log("entru")
           var a = document.createElement('a');
            a.href = pag[index]; // Insted of calling setAttribute 
            a.innerHTML = pag[index]; // <a>INNER_TEXT</a>
            cell.innerHTML = "";
            cell.appendChild(a); // Append the lin
        }       
    }   
}

function separar() {
    for (var index = 0; index < linhas.length; index++) {
        if (linhas[index].includes(".titulo")) {
            titulos.push(linhas[index]);
            titulosC.push(linhas[index]);

        }
        if (linhas[index].includes(".uva")) {
            uvas.push(linhas[index]);
            uvasC.push(linhas[index]);

        }
        if (linhas[index].includes(".classificacao")) {
            classificacaos.push(linhas[index]);
            classificacaosC.push(linhas[index]);
        }
        if (linhas[index].includes(".teorAlcoolico")) {
            teorAs.push(linhas[index]);
            teorAsC.push(linhas[index]);

        }
        if (linhas[index].includes(".volume")) {
            volumes.push(linhas[index]);
            volumesC.push(linhas[index]);

        }

    }

}

function buscar(exp, base, saida) {

    for (var index = 0; index < base.length; index++) {
        if (base[index].includes(exp) && exp !== "") {
            var temp = base[index].split(";");
            for (var j = 1; j < temp.length - 1; j++) {
                saida.push(temp[j]);
            }
            index = base.length;
        }

    }

}

function buscarC(exp, base, saida) {

    for (var index = 0; index < base.length; index++) {
        if (base[index].includes(exp) && exp !== "") {
            var temp = base[index].split(";");
            saida.push(temp[1]);
            for (var j = 2; j < temp.length - 1; j++) {
                saida.push(temp[j] + temp[1]);
            }
            index = base.length;
        }

    }

}

