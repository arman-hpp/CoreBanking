package com.bank.web.extensions.exporters;

public interface IExporterFactory {
    IExporter CreateExporter(ExportTypes exportType);
}
