import React, { useEffect, useState } from "react";
import { useTable } from "react-table";
import "./LjubimciDataTable.css";

interface Ljubimac {
  idLjubimac: number;
  imeLjubimac: string;
  vrsta: string;
  spol: string;
  dob: number;
  boja: string;
  prehrana: string;
  adresa: string;
  veterinar: string;
  imeVlasnika: string;
  prezimeVlasnika: string;
}

const LjubimciDataTable: React.FC = () => {
  const [data, setData] = useState<Ljubimac[]>([]);
  const [searchText, setSearchText] = useState<string>("");
  const [selectedAttribute, setSelectedAttribute] = useState<string>("all");

  useEffect(() => {
    fetchData("", selectedAttribute);
  }, []);

  const fetchData = async (
    searchText: string,
    attribute: string
  ): Promise<void> => {
    try {
      const response = await fetch(
        `http://localhost:8080/api/ljubimci/ljubimciIVlasnici?searchText=${searchText}&attribute=${attribute}`
      );
      const result: { status: string; message: string; response: Ljubimac[] } =
        await response.json();

      if (result.status === "OK") {
        setData(result.response);
      } else {
        console.error("Error fetching data:", result.message);
      }
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  const handleSearch = async (): Promise<void> => {
    fetchData(searchText, selectedAttribute);
  };

  const exportToCSV = async () => {
    try {
      const csvData = convertToCSV(data);
      downloadFile(csvData, "ljubimci.csv", "text/csv");
    } catch (error) {
      console.error("Error exporting to CSV:", error);
    }
  };

  const exportToJSON = async () => {
    try {
      const transformedData = data.map((ljubimac) => {
        return {
          id: ljubimac.idLjubimac,
          ime: ljubimac.imeLjubimac,
          vrsta: ljubimac.vrsta,
          spol: ljubimac.spol,
          dob: ljubimac.dob,
          boja: ljubimac.boja,
          prehrana: ljubimac.prehrana,
          adresa: ljubimac.adresa,
          veterinar: ljubimac.veterinar,
          vlasnik: {
            ime: ljubimac.imeVlasnika,
            prezime: ljubimac.prezimeVlasnika,
          },
        };
      });

      const jsonData = JSON.stringify(transformedData, null, 2);
      downloadFile(jsonData, "ljubimci.json", "application/json");
    } catch (error) {
      console.error("Error exporting to JSON:", error);
    }
  };

  const downloadFile = (data, fileName, fileType) => {
    const blob = new Blob([data], { type: fileType });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement("a");
    a.href = url;
    a.download = fileName;
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
  };

  const convertToCSV = (data) => {
    const headers = Object.keys(data[0]).join(",");
    const rows = data.map((row) => Object.values(row).join(","));
    return `${headers}\n${rows.join("\n")}`;
  };

  // react-table setup
  const columns = React.useMemo(
    () => [
      { Header: "ID", accessor: "idLjubimac" },
      { Header: "Ime ljubimca", accessor: "imeLjubimac" },
      { Header: "Vrsta", accessor: "vrsta" },
      { Header: "Spol", accessor: "spol" },
      { Header: "Dob", accessor: "dob" },
      { Header: "Boja", accessor: "boja" },
      { Header: "Prehrana", accessor: "prehrana" },
      { Header: "Adresa", accessor: "adresa" },
      { Header: "Veterinar", accessor: "veterinar" },
      { Header: "Ime vlasnika", accessor: "imeVlasnika" },
      { Header: "Prezime vlasnika", accessor: "prezimeVlasnika" },
    ],
    []
  );

  const { getTableProps, getTableBodyProps, headerGroups, rows, prepareRow } =
    useTable({ columns, data });

  return (
    <div className="dataTableContainer">
      <button id="indexBtn">
        <a href="index.html">Index.html</a>
      </button>
      <div className="navContainer">
        <div className="searchContainer">
          <label id="searchLabel" htmlFor="searchText">
            Polje za pretraživanje
          </label>
          <input
            type="text"
            id="searchText"
            value={searchText}
            onChange={(e) => setSearchText(e.target.value)}
            placeholder="Upisite vrijednost"
          />
        </div>
        <div className="attributeContainer">
          <label id="attributeLabel" htmlFor="attribute">
            Odaberi atribut:
          </label>
          <select
            id="attribute"
            value={selectedAttribute}
            onChange={(e) => setSelectedAttribute(e.target.value)}
          >
            <option value="all">Svi atributi</option>
            <option value="imeLjubimac">Ime ljubimca</option>
            <option value="vrsta">Vrsta</option>
            <option value="spol">Spol</option>
            <option value="dob">Dob</option>
            <option value="boja">Boja</option>
            <option value="prehrana">Prehrana</option>
            <option value="adresa">Adresa</option>
            <option value="veterinar">Veterinar</option>
            <option value="imeVlasnika">Ime vlasnika</option>
            <option value="prezimeVlasnika">Prezime vlasnika</option>
          </select>
        </div>

        <button id="searchBtn" onClick={handleSearch}>
          Pretraži
        </button>
        <button id="csvBtn" onClick={exportToCSV}>
          Export to CSV
        </button>
        <button id="jsonBtn" onClick={exportToJSON}>
          Export to JSON
        </button>
      </div>

      <table {...getTableProps()} style={{ marginTop: "20px" }}>
        <thead>
          {headerGroups.map((headerGroup) => (
            <tr {...headerGroup.getHeaderGroupProps()}>
              {headerGroup.headers.map((column) => (
                <th {...column.getHeaderProps()}>{column.render("Header")}</th>
              ))}
            </tr>
          ))}
        </thead>
        <tbody {...getTableBodyProps()}>
          {rows.map((row) => {
            prepareRow(row);
            return (
              <tr {...row.getRowProps()}>
                {row.cells.map((cell) => (
                  <td {...cell.getCellProps()}>{cell.render("Cell")}</td>
                ))}
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
};

export default LjubimciDataTable;
