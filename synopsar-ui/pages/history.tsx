"use client";
import React, {useEffect, useState} from "react";
import ReactMarkdown from "react-markdown";
import "./history.css";
import remarkGfm from "remark-gfm";
import NavBar from "./_navBar";
import Button from "@mui/material/Button";
import CircularProgress from "@mui/material/CircularProgress";
import {SummaryDto} from "@/dto/summaryDto";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import {Box, Modal} from "@mui/material";

function History() {
    const [history, setHistory] = useState([]);
    const [loading, setLoading] = useState(false);
    const [summary, setSummary] = useState("");
    const [currentItemSummary, setCurrentItemSummary] = useState("");

    useEffect(() => {
        getHistory();
    }, []);

    let getHistory = async () => {
        setLoading(true);
        let resp = await fetch(`http://localhost:8081/summary/history`, {
            method: "GET",
            credentials: "include",
            mode: "cors",
        });

        if (resp.ok) {
            const data = await resp.json();
            setHistory(data);
        }
        setLoading(false);
    };

    let deleteSummary = async (id: number) => {
        const resp = await fetch(`http://localhost:8081/summary/${id}`, {
            method: "DELETE",
            credentials: "include",
            mode: "cors",
        });

        if (resp.ok) {
            getHistory();
        }
    };

    return (
        <div className="container h-screen ">
            <NavBar/>
            <main className="flex flex-col justify-content-start p-12-page">
                <div className="items-center">
                    <h1>My Synopses</h1>

                    <div className="mt-16 h-auto flex w-full items-center justify-center">
                        {loading ? (
                            <div className="flex items-center justify-center">
                                <CircularProgress/>
                            </div>
                        ) : (
                            <div>
                                <Table sx={{minWidth: 650}}>
                                    <TableHead>
                                        <TableRow>
                                            <TableCell>Title</TableCell>
                                            <TableCell>URL</TableCell>
                                            <TableCell>Date</TableCell>
                                            <TableCell>Actions</TableCell>
                                        </TableRow>
                                    </TableHead>
                                    <TableBody>
                                        {history.map((item: SummaryDto) => (
                                            <TableRow key={item.id}>
                                                <TableCell>{item.title}</TableCell>
                                                <TableCell>
                                                    <a
                                                        href={item.url}
                                                        target="_blank"
                                                        rel="noopener noreferrer"
                                                    >
                                                        {item.url}
                                                    </a>
                                                </TableCell>
                                                <TableCell>
                                                    {new Date(item.date).toLocaleString("en-US", {
                                                        year: "numeric",
                                                        month: "numeric",
                                                        day: "numeric",
                                                        hour: "numeric",
                                                        minute: "numeric",
                                                        second: "numeric",
                                                    })}
                                                </TableCell>
                                                <TableCell>
                                                    <Button
                                                        variant="contained"
                                                        onClick={() => setCurrentItemSummary(item.text)}
                                                        size="small"
                                                        sx={{
                                                            width: "80%",
                                                            flex: "1 1 auto",
                                                        }}

                                                    >
                                                        View Synopsis
                                                    </Button>
                                                    <Button
                                                        variant="contained"
                                                        onClick={() => deleteSummary(item.id)}
                                                        size="small"
                                                        sx={{
                                                            width: "80%",
                                                            flex: "1 1 auto",
                                                            marginTop: "10px",
                                                        }}
                                                    >
                                                        Delete
                                                    </Button>
                                                </TableCell>
                                            </TableRow>
                                        ))}
                                    </TableBody>
                                </Table>

                                <Modal
                                    open={currentItemSummary !== ""}
                                    onClose={() => setCurrentItemSummary("")}
                                >
                                    <Box
                                        sx={{
                                            position: "relative",
                                            top: "50%",
                                            left: "50%",
                                            transform: "translate(-50%, -50%)",
                                            width: 600,
                                            height: 800,
                                            bgcolor: "background.paper",
                                            border: "2px solid #000",
                                            boxShadow: 24,
                                            p: 4,
                                            overflowY: "scroll",
                                        }}
                                    >
                                        <ReactMarkdown
                                            children={currentItemSummary}
                                            remarkPlugins={[remarkGfm]}
                                            textAlign="left"
                                        />
                                        <Button
                                            variant="contained"
                                            onClick={() => setCurrentItemSummary("")}
                                        >
                                            Close
                                        </Button>
                                    </Box>
                                </Modal>
                            </div>
                        )}

                        <div className="flex items-center justify-center pt-2">
                            <Button
                                variant="contained"
                                onClick={getHistory}
                                disabled={loading}
                                className="mr-12 pt-2"
                            >
                                {loading ? "Loading..." : "Refresh"}
                            </Button>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    );
}

export default History;

