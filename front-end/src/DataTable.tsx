  import { useState, useEffect, useMemo } from "react";
import { useTable, useSortBy } from "react-table";
import { saveAs } from "file-saver";
import "./DataTable.css"

const DataTable = () => {
  const [data, setData] = useState([]);
  const [searchText, setSearchText] = useState("");
  const [selectedAttribute, setSelectedAttribute] = useState("all");
  const [filteredData, setFilteredData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/igracke");
        const result = await response.json();
        setData(result);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, []);

  const handleSearch = () => {
    // Filtriranje podataka kada se pritisne gumb "Pretraži"
    const newFilteredData = data.filter((item) => {
      if (selectedAttribute === "all") {
        // Pretražuj sve atribute
        return (
          Object.values(item).some((value) =>
            String(value).toLowerCase().includes(searchText.toLowerCase())
          ) ||
          Object.values(item.ljubimac).some((value) =>
            String(value).toLowerCase().includes(searchText.toLowerCase())
          )
        );
      } else {
        // Pretražuj samo odabrani atribut
        const attributeValue =
          item[selectedAttribute] || item.ljubimac[selectedAttribute];
        return (
          attributeValue &&
          String(attributeValue)
            .toLowerCase()
            .includes(searchText.toLowerCase())
        );
      }
    });

    setFilteredData(newFilteredData);
  };

  const columns = useMemo(
    () => [
      { Header: "Ime ljubimca", accessor: "ljubimac.ime" },
      { Header: "Vrsta", accessor: "ljubimac.vrsta" },
      { Header: "Spol", accessor: "ljubimac.spol" },
      { Header: "Boja Ljubimca", accessor: "ljubimac.boja" },
      { Header: "Starost", accessor: "ljubimac.starost" },
      { Header: "Ime vlasnika", accessor: "ljubimac.imeVlasnika" },
      { Header: "Prehrana", accessor: "ljubimac.prehrana" },
      { Header: "Životni vijek", accessor: "ljubimac.zivotniVijek" },
      { Header: "Naziv Igracke", accessor: "naziv" },
      { Header: "Boja Igracke", accessor: "boja" },
    ],
    []
  );

  const { getTableProps, getTableBodyProps, headerGroups, rows, prepareRow } =
    useTable(
      {
        columns,
        data: searchText ? filteredData : data, // Prikazujemo filtrirane podatke samo ako postoji tekst za pretraživanje
      },
      useSortBy
    );

  // Funkcija za generiranje JSON podataka
  const generateJSON = async () => {
    try {
      // Dohvati podatke ako nisu preuzeti
      if (!data || data.length === 0) {
        const response = await fetch("http://localhost:8080/api/igracke");
        const result = await response.json();
        setData(result);
      }

      // Provjeri jesu li podaci dostupni
      if (!data || data.length === 0) {
        console.error("No data available.");
        return;
      }

      // Pripremi podatke za JSON
      const jsonData = JSON.stringify(data, null, 2);

      // Kreiraj Blob objekt s JSON podacima
      const blob = new Blob([jsonData], {
        type: "application/json;charset=utf-8",
      });

      // Snimi JSON datoteku
      saveAs(blob, "data.json");
    } catch (error) {
      console.error("Error generating JSON:", error);
    }
  };

  // Funkcija za generiranje CSV podataka
  const generateCSV = () => {
    if (!rows || rows.length === 0 || !columns || columns.length === 0) {
      console.error("Rows or columns are not defined.");
      return;
    }

    // Priprema zaglavlja CSV-a
    const header = columns.map((column) => column.Header);

    // Priprema podataka za CSV
    const csvData = rows.map((row) => {
      return row.cells.map((cell) => cell.value);
    });

    // Spajanje zaglavlja i podataka CSV-a
    const csvContent = [header, ...csvData]
      .map((row) => row.join(","))
      .join("\n");

    // Kreiranje CSV datoteke
    const blob = new Blob([csvContent], { type: "text/csv;charset=utf-8;" });
    const filename = "data.csv";

    // Snimanje CSV datoteke
    if (navigator.msSaveBlob) {
      // Internet Explorer
      navigator.msSaveBlob(blob, filename);
    } else {
      // Ostali preglednici
      const link = document.createElement("a");
      if (link.download !== undefined) {
        const url = URL.createObjectURL(blob);
        link.setAttribute("href", url);
        link.setAttribute("download", filename);
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
      } else {
        console.error("Browser does not support file download.");
      }
    }
  };

  return (
    <div>
      <div>
        <label>
          Tekst za pretraživanje:
          <input
            type="text"
            value={searchText}
            onChange={(e) => setSearchText(e.target.value)}
          />
        </label>
        <br />
        <label>
          Odaberi atribut:
          <select
            value={selectedAttribute}
            onChange={(e) => setSelectedAttribute(e.target.value)}
          >
            <option value="all">Svi atributi</option>
            {columns.map((column) => (
              <option
                key={column.accessor}
                value={column.accessor.split(".")[1]}
              >
                {column.Header}
              </option>
            ))}
          </select>
        </label>
        <br />
        <button onClick={handleSearch}>Pretraži</button>
        <br />
        <br />
      </div>
      <table {...getTableProps()} border="1">
        <thead>
          {headerGroups.map((headerGroup) => (
            <tr {...headerGroup.getHeaderGroupProps()}>
              {headerGroup.headers.map((column) => (
                <th {...column.getHeaderProps(column.getSortByToggleProps())}>
                  {column.render("Header")}
                  {column.isSorted ? (column.isSortedDesc ? " ↓" : " ↑") : " ↕"}
                </th>
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
      <div className="buttons">
      {/* Link za preuzimanje JSON-a */}
      <button onClick={generateJSON} className="json">Preuzmi JSON</button>

      {/* Link za preuzimanje CSV-a */}
      <button onClick={generateCSV} className="csv">Preuzmi CSV</button>
      </div>
    </div>
  );
};

export default DataTable;
